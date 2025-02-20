package com.linjiasong.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linjiasong.user.constant.UserInfoContext;
import com.linjiasong.user.entity.UserInfo;
import com.linjiasong.user.entity.dto.*;
import com.linjiasong.user.entity.vo.*;
import com.linjiasong.user.excepiton.UserBaseResponse;
import com.linjiasong.user.feign.ArticleServiceClient;
import com.linjiasong.user.gateway.UserGateway;
import com.linjiasong.user.point.dto.PointArticleDTO;
import com.linjiasong.user.point.service.PointService;
import com.linjiasong.user.point.enums.PointTypeEnum;
import com.linjiasong.user.service.UserArticleService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author linjiasong
 * @date 2025/01/15 21:16
 */
@Service
@Slf4j
public class UserArticleServiceImpl implements UserArticleService {

    @Autowired
    ArticleServiceClient articleServiceClient;

    @Autowired
    UserGateway userGateway;

    @Autowired
    PointService pointService;

    private static final String BREAKER = "userCircuitBreaker";
    private static final String INDEX_FALLBACK_METHOD_NAME = "getUserArticleBasicFallBack";

    @Override
    public UserBaseResponse<?> getUserArticleBasic(int current, int size) {
        return articleServiceClient.getArticleBasicByUserId(UserInfoContext.get().getId(), current, size);
    }

    @Override
    public UserBaseResponse<?> createArticle(ArticleCreateDTO articleCreateDTO) {
        return articleServiceClient.createArticle(articleCreateDTO);
    }

    @Override
    public UserBaseResponse<?> updateArticle(ArticleUpdateDTO articleUpdateDTO) {
        return articleServiceClient.updateArticle(articleUpdateDTO);
    }

    @Override
    public UserBaseResponse<?> deleteArticle(Long id) {
        return articleServiceClient.deleteArticle(id);
    }

    @Override
    public UserBaseResponse<?> collect(Long articleId) {
        return articleServiceClient.collect(articleId);
    }

    @Override
    public UserBaseResponse<?> like(Long articleId) {
        return articleServiceClient.like(articleId);
    }

    @Override
    public UserBaseResponse<?> getArticleDetail(Long articleId) {
        UserBaseResponse<?> response = articleServiceClient.getArticleDetail(articleId);
        if (!response.getCode().equals("200")) {
            return response;
        }
        ArticleDetailVO articleDetailVO = JSON.parseObject(JSON.toJSONString(response.getData()), ArticleDetailVO.class);
        setArticleDetailVOUserInfo(articleDetailVO);

        if (!articleDetailVO.getUserInfo().getUserId().equals(UserInfoContext.get().getId())) {
            pointService.execute(PointTypeEnum.POINT_ARTICLE, PointArticleDTO.build(articleId));
        }

        return UserBaseResponse.success(articleDetailVO);
    }

    @Override
    public UserBaseResponse<?> getSelfArticleDetail(Long articleId) {
        return articleServiceClient.getSelfArticleDetail(articleId);
    }

    @Override
    public UserBaseResponse<?> comment(ArticleCommentDTO articleCommentDTO) {
        return articleServiceClient.comment(articleCommentDTO);
    }

    @Override
    public UserBaseResponse<?> deleteComment(Long id) {
        return articleServiceClient.deleteComment(id);
    }

    @Override
    public UserBaseResponse<?> getArticleComments(Long articleId) {
        UserBaseResponse response = articleServiceClient.getArticleComments(articleId);
        ArticleCommentVO articleCommentVO = JSON.parseObject((String) response.getData(), ArticleCommentVO.class);

        List<UserInfo> userInfos = userGateway.selectByIds(articleCommentVO.getArticleCommentInfoList().stream().map(ArticleCommentVO.ArticleCommentInfo::getUserId).distinct().collect(Collectors.toList()));

        return UserBaseResponse.success(ArticleUserCommentVO.build(articleCommentVO.getArticleCommentInfoList(), userInfos));
    }

    @Override
    public UserBaseResponse<?> openArticle(Long articleId) {
        return articleServiceClient.openArticle(articleId);
    }

    @Override
    public UserBaseResponse<?> hotArticle() {
        return articleServiceClient.hotArticle();
    }

    @Override
    public UserBaseResponse<?> getArticleUserWatchList() {
        return articleServiceClient.getUserWatchArticleList();
    }

    @Override
    public UserBaseResponse<?> deleteUserWatch(ArticleDeleteUserWatchDTO articleDeleteUserWatchDTO) {
        if (!articleDeleteUserWatchDTO.hasData()) {
            return UserBaseResponse.success();
        }

        return articleServiceClient.deleteUserWatch(articleDeleteUserWatchDTO);
    }

    @CircuitBreaker(name = BREAKER, fallbackMethod = INDEX_FALLBACK_METHOD_NAME)
    @Override
    public UserBaseResponse<?> getArticleList(ArticlePageSelectDTO articlePageSelectDTO) {
        return articleServiceClient.getIndexArticle(articlePageSelectDTO);
    }

    public UserBaseResponse<?> getUserArticleBasicFallBack(ArticlePageSelectDTO articlePageSelectDTO, Throwable throwable) {
        log.info("getUserArticleBasicFallBack = {}", throwable.getMessage());
        Page<Object> page = new Page<>();
        page.setCurrent(articlePageSelectDTO.getCurrent());
        page.setPages(1);
        page.setRecords(new ArrayList<>());
        page.setTotal(0);
        page.setSize(articlePageSelectDTO.getSize());
        return UserBaseResponse.success(page);
    }

    @Override
    public UserBaseResponse<?> userHasCollect(Long articleId) {
        return articleServiceClient.userHasCollect(articleId);
    }

    @Override
    public UserBaseResponse<?> userHasLike(Long articleId) {
        return articleServiceClient.userHasLike(articleId);
    }

    @Override
    public UserBaseResponse<?> getUserLikeArticles(int current, int size) {
        UserBaseResponse<?> userLikeArticles = articleServiceClient.getUserLikeArticles(current, size);
        ArticleLikeDTO articleLikeDTO = JSON.parseObject(userLikeArticles.getData().toString(), ArticleLikeDTO.class);

        List<Long> userIds = articleLikeDTO.getLikeDataList().stream().map(ArticleLikeDTO.LikeData::getUserId).toList();
        Map<Long, UserInfo> userMap = userIds.isEmpty() ? Map.of() : userGateway.selectByIds(userIds).stream().collect(Collectors.toMap(UserInfo::getId, userInfo -> userInfo));

        return UserBaseResponse.success(ArticleLikeVO.build(articleLikeDTO, userMap));
    }

    @Override
    public UserBaseResponse<?> getUserCollectArticles(int current, int size) {
        UserBaseResponse<?> userCollectArticles = articleServiceClient.getUserCollectArticles(current, size);
        ArticleCollectDTO articleCollectDTO = JSON.parseObject(userCollectArticles.getData().toString(), ArticleCollectDTO.class);

        List<Long> userIds = articleCollectDTO.getCollectDataList().stream().map(ArticleCollectDTO.CollectData::getUserId).toList();
        Map<Long, UserInfo> userMap = userIds.isEmpty() ? Map.of() : userGateway.selectByIds(userIds).stream().collect(Collectors.toMap(UserInfo::getId, userInfo -> userInfo));

        return UserBaseResponse.success(ArticleCollectVO.build(articleCollectDTO, userMap));
    }

    private void setArticleDetailVOUserInfo(ArticleDetailVO articleDetailVO) {
        UserInfo userInfo = userGateway.selectById(articleDetailVO.getUserInfo().getUserId());

        ArticleDetailVO.UserInfo userInfo1 = articleDetailVO.getUserInfo();
        userInfo1.setUsername(userInfo.getUsername());
        userInfo1.setImage(userInfo.getImage());

        articleDetailVO.setUserInfo(userInfo1);
    }
}

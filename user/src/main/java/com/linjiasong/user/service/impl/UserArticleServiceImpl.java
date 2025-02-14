package com.linjiasong.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.linjiasong.user.constant.UserInfoContext;
import com.linjiasong.user.entity.UserInfo;
import com.linjiasong.user.entity.dto.*;
import com.linjiasong.user.entity.vo.ArticleCommentVO;
import com.linjiasong.user.entity.vo.ArticleDetailVO;
import com.linjiasong.user.entity.vo.ArticleUserCommentVO;
import com.linjiasong.user.excepiton.UserBaseResponse;
import com.linjiasong.user.feign.ArticleServiceClient;
import com.linjiasong.user.gateway.UserGateway;
import com.linjiasong.user.point.dto.PointArticleDTO;
import com.linjiasong.user.point.service.PointService;
import com.linjiasong.user.point.enums.PointTypeEnum;
import com.linjiasong.user.service.UserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author linjiasong
 * @date 2025/01/15 21:16
 */
@Service
public class UserArticleServiceImpl implements UserArticleService {

    @Autowired
    ArticleServiceClient articleServiceClient;

    @Autowired
    UserGateway userGateway;

    @Autowired
    PointService pointService;

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

    @Override
    public UserBaseResponse<?> getArticleList(ArticlePageSelectDTO articlePageSelectDTO) {
        return articleServiceClient.getIndexArticle(articlePageSelectDTO);
    }

    @Override
    public UserBaseResponse<?> userHasCollect(Long articleId) {
        return articleServiceClient.userHasCollect(articleId);
    }

    @Override
    public UserBaseResponse<?> userHasLike(Long articleId) {
        return articleServiceClient.userHasLike(articleId);
    }

    private void setArticleDetailVOUserInfo(ArticleDetailVO articleDetailVO) {
        UserInfo userInfo = userGateway.selectById(articleDetailVO.getUserInfo().getUserId());

        ArticleDetailVO.UserInfo userInfo1 = articleDetailVO.getUserInfo();
        userInfo1.setUsername(userInfo.getUsername());
        userInfo1.setImage(userInfo.getImage());

        articleDetailVO.setUserInfo(userInfo1);
    }
}

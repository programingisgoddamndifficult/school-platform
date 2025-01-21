package com.linjiasong.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.linjiasong.user.constant.UserInfoContext;
import com.linjiasong.user.entity.UserInfo;
import com.linjiasong.user.entity.dto.ArticleCommentDTO;
import com.linjiasong.user.entity.dto.ArticleCreateDTO;
import com.linjiasong.user.entity.dto.ArticleUpdateDTO;
import com.linjiasong.user.entity.vo.ArticleCommentVO;
import com.linjiasong.user.entity.vo.ArticleUserCommentVO;
import com.linjiasong.user.excepiton.UserBaseResponse;
import com.linjiasong.user.feign.ArticleServiceClient;
import com.linjiasong.user.gateway.UserGateway;
import com.linjiasong.user.service.UserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

    @Override
    public UserBaseResponse<?> getUserArticleBasic() {
        return articleServiceClient.getArticleBasicByUserId(UserInfoContext.get().getId());
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
        return articleServiceClient.getArticleDetail(articleId);
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
}

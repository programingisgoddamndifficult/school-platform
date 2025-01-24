package com.linjiasong.user.service;

import com.linjiasong.user.entity.dto.ArticleCommentDTO;
import com.linjiasong.user.entity.dto.ArticleCreateDTO;
import com.linjiasong.user.entity.dto.ArticleUpdateDTO;
import com.linjiasong.user.excepiton.UserBaseResponse;

/**
 * @author linjiasong
 * @date 2025/01/15 21:16
 */
public interface UserArticleService {

    /**
     * 获取文章基础信息
     * @return UserBaseResponse
     */
    UserBaseResponse<?> getUserArticleBasic();

    /**
     * 创建文章
     * @param articleCreateDTO articleCreateDTO
     * @return UserBaseResponse
     */
    UserBaseResponse<?> createArticle(ArticleCreateDTO articleCreateDTO);

    /**
     * 修改文章
     * @param articleUpdateDTO articleUpdateDTO
     * @return UserBaseResponse
     */
    UserBaseResponse<?> updateArticle(ArticleUpdateDTO articleUpdateDTO);

    /**
     * 删除文章
     * @param id id
     * @return UserBaseResponse
     */
    UserBaseResponse<?> deleteArticle(Long id);

    /**
     * 收藏/取消收藏文章
     * @param articleId articleId
     * @return UserBaseResponse
     */
    UserBaseResponse<?> collect(Long articleId);

    /**
     * 点赞/取消点赞
     * @param articleId articleId
     * @return UserBaseResponse
     */
    UserBaseResponse<?> like(Long articleId);

    /**
     * 获取文章详情
     * @param articleId articleId
     * @return UserBaseResponse
     */
    UserBaseResponse<?> getArticleDetail(Long articleId);

    /**
     * 评论
     * @param articleCommentDTO articleCommentDTO
     * @return UserBaseResponse
     */
    UserBaseResponse<?> comment(ArticleCommentDTO articleCommentDTO);

    /**
     *
     * @param id id
     * @return UserBaseResponse
     */
    UserBaseResponse<?> deleteComment(Long id);

    /**
     * 获取文章的评论
     * @param articleId articleId
     * @return UserBaseResponse
     */
    UserBaseResponse<?> getArticleComments(Long articleId);

    /**
     * 开放/私密文章
     * @param articleId articleId
     * @return UserBaseResponse
     */
    UserBaseResponse<?> openArticle(Long articleId);

    /**
     * 文章hot100
     * @return UserBaseResponse
     */
    UserBaseResponse<?> hotArticle();

    /**
     * 个人观看历史
     * @return UserBaseResponse
     */
    UserBaseResponse<?> getArticleUserWatchList();
}

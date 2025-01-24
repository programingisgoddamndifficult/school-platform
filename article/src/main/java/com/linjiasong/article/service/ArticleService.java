package com.linjiasong.article.service;

import com.linjiasong.article.entity.dto.ArticleCreateDTO;
import com.linjiasong.article.entity.dto.ArticleDeleteUserWatchDTO;
import com.linjiasong.article.entity.dto.ArticleUpdateDTO;
import com.linjiasong.article.excepiton.ArticleBaseResponse;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:39
 */
public interface ArticleService {

    /**
     * 新建文章
     * @param articleCreateDTO articleCreateDTO
     * @return ArticleBaseResponse
     */
    ArticleBaseResponse<?> createArticle(ArticleCreateDTO articleCreateDTO);


    /**
     * 获取用户的文章
     * @param userId userId
     * @return ArticleBaseResponse
     */
    ArticleBaseResponse<?> getUserArticleBasic(Long userId);

    /**
     * 修改文章
     * @param articleUpdateDTO articleUpdateDTO
     * @return ArticleBaseResponse
     */
    ArticleBaseResponse<?> updateArticle(ArticleUpdateDTO articleUpdateDTO);

    /**
     * 删除文章
     * @param id id
     * @return ArticleBaseResponse
     */
    ArticleBaseResponse<?> deleteArticle(Long id);

    /**
     * 获取文章详情
     * @param articleId articleId
     * @return ArticleBaseResponse
     */
    ArticleBaseResponse<?> getArticleDetail(Long articleId);

    /**
     * 开放文章
     * @param articleId articleId
     * @return ArticleBaseResponse
     */
    ArticleBaseResponse<?> openArticle(Long articleId);

    /**
     * 获取热门文章
     * @return ArticleBaseResponse
     */
    ArticleBaseResponse<?> getHotArticle();

    /**
     * 获取个人观看历史列表
     * @return ArticleBaseResponse
     */
    ArticleBaseResponse<?> getUserWatchList();

    /**
     * 删除用户观看历史
     * @param articleDeleteUserWatchDTO articleDeleteUserWatchDTO
     * @return ArticleBaseResponse
     */
    ArticleBaseResponse<?> deleteUserWatch(ArticleDeleteUserWatchDTO articleDeleteUserWatchDTO);

    /**
     * 获取推荐列表
     * @return ArticleBaseResponse
     */
    ArticleBaseResponse<?> getArticleIndexList();
}

package com.linjiasong.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linjiasong.article.entity.ArticleCollect;
import com.linjiasong.article.excepiton.ArticleBaseResponse;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:08
 */
public interface ArticleCollectService extends IService<ArticleCollect> {

    /**
     * 收藏/取消收藏文章
     * @param articleId articleId
     * @return ArticleBaseResponse
     */
    ArticleBaseResponse<?> collect(Long articleId);

    /**
     * 用户是否已收藏
     * @param articleId articleId
     * @return ArticleBaseResponse
     */
    ArticleBaseResponse<?> userHasCollect(Long articleId);

    /**
     * 获取用户收藏的文章列表
     *
     * @return ArticleBaseResponse
     */
    ArticleBaseResponse<?> getUserCollectArticles(int current, int size);
}

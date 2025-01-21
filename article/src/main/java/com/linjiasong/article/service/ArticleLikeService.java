package com.linjiasong.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linjiasong.article.entity.ArticleLike;
import com.linjiasong.article.excepiton.ArticleBaseResponse;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:18
 */
public interface ArticleLikeService extends IService<ArticleLike> {

    /**
     * 点赞/取消点赞
     * @param articleId articleId
     * @return ArticleBaseResponse
     */
    ArticleBaseResponse like(Long articleId);

}

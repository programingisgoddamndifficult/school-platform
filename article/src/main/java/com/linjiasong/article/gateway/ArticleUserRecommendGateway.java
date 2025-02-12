package com.linjiasong.article.gateway;

import com.linjiasong.article.entity.ArticleUserRecommend;

/**
 * @author linjiasong
 * @date 2025/2/11 下午5:36
 */
public interface ArticleUserRecommendGateway {

    boolean hasRecommendHistory();

    boolean updateOrInsert(ArticleUserRecommend recommend);

    Long getRecommendBigArticleId();

    ArticleUserRecommend getByUserId(Long userId);
}

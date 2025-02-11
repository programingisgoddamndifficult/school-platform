package com.linjiasong.article.gateway;

import com.linjiasong.article.entity.ArticleUserRecommend;

/**
 * @author linjiasong
 * @date 2025/2/11 下午5:36
 */
public interface ArticleUserRecommendGateway {

    boolean hasRecommendHistory();

    boolean insert(ArticleUserRecommend recommend);

    Long getRecommendBigArticleId();
}

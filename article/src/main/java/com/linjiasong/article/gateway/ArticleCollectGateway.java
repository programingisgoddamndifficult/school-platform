package com.linjiasong.article.gateway;

import com.linjiasong.article.entity.ArticleBasicInfo;

/**
 * @author linjiasong
 * @date 2025/1/20 下午5:56
 */
public interface ArticleCollectGateway {

    boolean collect(Long articleId);

}

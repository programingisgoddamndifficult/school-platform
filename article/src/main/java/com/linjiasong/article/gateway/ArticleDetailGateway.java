package com.linjiasong.article.gateway;

import com.linjiasong.article.entity.ArticleDetail;

import java.util.List;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:42
 */
public interface ArticleDetailGateway {

    boolean insert(ArticleDetail articleDetail);


    List<ArticleDetail> getByUserId(Long userId);
}

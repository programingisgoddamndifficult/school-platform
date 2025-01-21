package com.linjiasong.article.gateway;

import com.linjiasong.article.entity.ArticleComment;

/**
 * @author linjiasong
 * @date 2025/1/21 下午6:00
 */
public interface ArticleCommentGateway {

    boolean comment(ArticleComment articleComment);

}

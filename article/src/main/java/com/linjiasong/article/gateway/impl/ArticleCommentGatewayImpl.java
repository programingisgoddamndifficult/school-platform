package com.linjiasong.article.gateway.impl;

import com.linjiasong.article.entity.ArticleComment;
import com.linjiasong.article.gateway.ArticleCommentGateway;
import com.linjiasong.article.mapper.ArticleCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/21 下午6:00
 */
@Service
public class ArticleCommentGatewayImpl implements ArticleCommentGateway {

    @Autowired
    ArticleCommentMapper articleCommentMapper;

    @Override
    public boolean comment(ArticleComment articleComment) {
        return articleCommentMapper.insert(articleComment) > 0;
    }
}

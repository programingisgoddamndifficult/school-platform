package com.linjiasong.article.gateway.impl;

import com.linjiasong.article.entity.ArticleDetail;
import com.linjiasong.article.gateway.ArticleDetailGateway;
import com.linjiasong.article.mapper.ArticleDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:42
 */
@Service
public class ArticleDetailGatewayImpl implements ArticleDetailGateway {

    @Autowired
    private ArticleDetailMapper articleDetailMapper;

    @Override
    public boolean insert(ArticleDetail articleDetail) {
        return articleDetailMapper.insert(articleDetail) > 0;
    }
}

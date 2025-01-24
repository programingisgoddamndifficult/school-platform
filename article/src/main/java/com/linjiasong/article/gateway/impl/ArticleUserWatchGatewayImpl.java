package com.linjiasong.article.gateway.impl;

import com.linjiasong.article.entity.ArticleUserWatch;
import com.linjiasong.article.gateway.ArticleUserWatchGateway;
import com.linjiasong.article.mapper.ArticleUserWatchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/24 上午10:26
 */
@Service
public class ArticleUserWatchGatewayImpl implements ArticleUserWatchGateway {

    @Autowired
    private ArticleUserWatchMapper articleUserWatchMapper;

    @Override
    public boolean insert(ArticleUserWatch articleUserWatch) {
        return articleUserWatchMapper.insert(articleUserWatch) > 0;
    }
}

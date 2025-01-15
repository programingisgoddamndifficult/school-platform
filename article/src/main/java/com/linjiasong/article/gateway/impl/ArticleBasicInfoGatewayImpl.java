package com.linjiasong.article.gateway.impl;

import com.linjiasong.article.entity.ArticleBasicInfo;
import com.linjiasong.article.gateway.ArticleBasicInfoGateway;
import com.linjiasong.article.mapper.ArticleBasicInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:42
 */
@Service
public class ArticleBasicInfoGatewayImpl implements ArticleBasicInfoGateway {

    @Autowired
    ArticleBasicInfoMapper articleBasicInfoMapper;

    @Override
    public boolean insert(ArticleBasicInfo articleBasicInfo) {
       return articleBasicInfoMapper.insert(articleBasicInfo) > 0;
    }
}

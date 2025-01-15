package com.linjiasong.article.service.impl;

import com.linjiasong.article.gateway.ArticleBasicInfoGateway;
import com.linjiasong.article.gateway.ArticleDetailGateway;
import com.linjiasong.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:39
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleBasicInfoGateway articleBasicInfoGateway;

    @Autowired
    ArticleDetailGateway articleDetailGateway;

}

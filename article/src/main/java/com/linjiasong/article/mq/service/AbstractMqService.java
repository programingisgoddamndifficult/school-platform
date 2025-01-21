package com.linjiasong.article.mq.service;

import com.linjiasong.article.gateway.ArticleBasicInfoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/21 下午4:05
 */
@Service
public abstract class AbstractMqService {

    @Autowired
    ArticleBasicInfoGateway articleBasicInfoGateway;

    abstract public void consume(String mqData);

}

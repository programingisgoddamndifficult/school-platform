package com.linjiasong.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linjiasong.article.entity.ArticleCollect;
import com.linjiasong.article.excepiton.ArticleBaseResponse;
import com.linjiasong.article.excepiton.BizException;
import com.linjiasong.article.gateway.ArticleCollectGateway;
import com.linjiasong.article.mapper.ArticleCollectMapper;
import com.linjiasong.article.service.ArticleCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:09
 */
@Service
public class ArticleCollectServiceImpl extends ServiceImpl<ArticleCollectMapper, ArticleCollect> implements ArticleCollectService {

    @Autowired
    ArticleCollectGateway articleCollectGateway;

    @Override
    public ArticleBaseResponse collect(Long articleId) {


        if(!articleCollectGateway.collect(articleId)){
            throw new BizException("服务异常");
        }

        return ArticleBaseResponse.success();
    }
}

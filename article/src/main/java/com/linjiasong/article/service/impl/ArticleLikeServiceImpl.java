package com.linjiasong.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linjiasong.article.entity.ArticleLike;
import com.linjiasong.article.excepiton.ArticleBaseResponse;
import com.linjiasong.article.excepiton.BizException;
import com.linjiasong.article.gateway.ArticleLikeGateway;
import com.linjiasong.article.mapper.ArticleLikeMapper;
import com.linjiasong.article.service.ArticleLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:19
 */
@Service
public class ArticleLikeServiceImpl extends ServiceImpl<ArticleLikeMapper, ArticleLike> implements ArticleLikeService {

    @Autowired
    private ArticleLikeGateway articleLikeGateway;

    @Override
    public ArticleBaseResponse like(Long articleId) {
        if(!articleLikeGateway.like(articleId)){
            throw new BizException("服务异常");
        }

        return ArticleBaseResponse.success();
    }
}

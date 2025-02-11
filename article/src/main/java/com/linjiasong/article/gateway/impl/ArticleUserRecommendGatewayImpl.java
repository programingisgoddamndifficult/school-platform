package com.linjiasong.article.gateway.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linjiasong.article.constant.ArticleContext;
import com.linjiasong.article.entity.ArticleUserRecommend;
import com.linjiasong.article.entity.ArticleUserWatch;
import com.linjiasong.article.gateway.ArticleUserRecommendGateway;
import com.linjiasong.article.mapper.ArticleUserRecommendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author linjiasong
 * @date 2025/2/11 下午5:37
 */
@Service
public class ArticleUserRecommendGatewayImpl implements ArticleUserRecommendGateway {

    @Autowired
    private ArticleUserRecommendMapper articleUserRecommendMapper;

    @Override
    public boolean hasRecommendHistory() {
        return articleUserRecommendMapper.selectOne(new QueryWrapper<ArticleUserRecommend>()
                .eq("user_id", ArticleContext.get().getId())) != null;
    }

    @Override
    public boolean insert(ArticleUserRecommend recommend) {
        return articleUserRecommendMapper.insert(recommend) > 0;
    }

    @Override
    public Long getRecommendBigArticleId() {
        return articleUserRecommendMapper.selectOne(new QueryWrapper<ArticleUserRecommend>()
                .eq("user_id", ArticleContext.get().getId())).getBigArticleId();
    }
}

package com.linjiasong.article.gateway.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linjiasong.article.constant.ArticleContext;
import com.linjiasong.article.entity.ArticleUserWatch;
import com.linjiasong.article.gateway.ArticleUserWatchGateway;
import com.linjiasong.article.mapper.ArticleUserWatchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<ArticleUserWatch> getUserWatchList() {
        return articleUserWatchMapper.selectList(new QueryWrapper<ArticleUserWatch>().eq("user_id", ArticleContext.get().getId()));
    }

    @Override
    public boolean isExist(Long articleId, Long userId) {
        return articleUserWatchMapper.selectOne(new QueryWrapper<ArticleUserWatch>()
                .eq("user_id", userId)
                .eq("article_id", articleId)) != null;
    }

    @Override
    public boolean deleteUserWatch(List<Long> ids) {
        return articleUserWatchMapper.deleteByIds(ids) > 0;
    }

    @Override
    public List<ArticleUserWatch> checkAndGetUserWatchList(List<Long> ids, Long userId) {
        return articleUserWatchMapper.selectList(new QueryWrapper<ArticleUserWatch>()
                .eq("user_id", userId)
                .in("id", ids));
    }
}

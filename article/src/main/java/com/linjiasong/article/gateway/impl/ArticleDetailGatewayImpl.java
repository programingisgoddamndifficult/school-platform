package com.linjiasong.article.gateway.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linjiasong.article.entity.ArticleDetail;
import com.linjiasong.article.gateway.ArticleDetailGateway;
import com.linjiasong.article.mapper.ArticleDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;

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

    @Override
    public boolean update(ArticleDetail articleDetail) {
        return articleDetailMapper.updateById(articleDetail) > 0;
    }

    @Override
    public boolean update(ArticleDetail articleDetail, QueryWrapper<ArticleDetail> queryWrapper) {
        return articleDetailMapper.update(articleDetail, queryWrapper) > 0;
    }

    @Override
    public List<ArticleDetail> getByUserId(Long userId) {
        return articleDetailMapper.selectList(new QueryWrapper<ArticleDetail>().eq("user_id", userId));
    }
}

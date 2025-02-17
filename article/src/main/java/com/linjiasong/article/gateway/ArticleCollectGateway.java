package com.linjiasong.article.gateway;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linjiasong.article.entity.ArticleCollect;

/**
 * @author linjiasong
 * @date 2025/1/20 下午5:56
 */
public interface ArticleCollectGateway {

    boolean collect(Long articleId);

    boolean userHasCollect(Long articleId);

    Page<ArticleCollect> selectList(int current, int size, QueryWrapper<ArticleCollect> queryWrapper);
}

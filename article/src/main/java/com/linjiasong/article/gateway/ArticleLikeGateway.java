package com.linjiasong.article.gateway;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linjiasong.article.entity.ArticleLike;

/**
 * @author linjiasong
 * @date 2025/1/21 上午11:02
 */
public interface ArticleLikeGateway {

    boolean like(Long articleId);

    boolean userHasLike(Long articleId);

    Page<ArticleLike> selectList(int current, int size, QueryWrapper<ArticleLike> queryWrapper);
}

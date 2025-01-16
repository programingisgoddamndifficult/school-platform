package com.linjiasong.article.gateway;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linjiasong.article.entity.ArticleDetail;

import java.sql.Wrapper;
import java.util.List;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:42
 */
public interface ArticleDetailGateway {

    boolean insert(ArticleDetail articleDetail);

    boolean update(ArticleDetail articleDetail);

    boolean update(ArticleDetail articleDetail, QueryWrapper<ArticleDetail> queryWrapper);

    List<ArticleDetail> getByUserId(Long userId);
}

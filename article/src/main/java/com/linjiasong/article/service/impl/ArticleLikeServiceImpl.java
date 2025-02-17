package com.linjiasong.article.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linjiasong.article.constant.ArticleContext;
import com.linjiasong.article.entity.ArticleBasicInfo;
import com.linjiasong.article.entity.ArticleLike;
import com.linjiasong.article.entity.vo.ArticleLikeVO;
import com.linjiasong.article.excepiton.ArticleBaseResponse;
import com.linjiasong.article.excepiton.BizException;
import com.linjiasong.article.gateway.ArticleBasicInfoGateway;
import com.linjiasong.article.gateway.ArticleLikeGateway;
import com.linjiasong.article.mapper.ArticleLikeMapper;
import com.linjiasong.article.service.ArticleLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:19
 */
@Service
public class ArticleLikeServiceImpl extends ServiceImpl<ArticleLikeMapper, ArticleLike> implements ArticleLikeService {

    @Autowired
    private ArticleLikeGateway articleLikeGateway;

    @Autowired
    private ArticleBasicInfoGateway articleBasicInfoGateway;

    @Override
    public ArticleBaseResponse<?> like(Long articleId) {
        if (!articleLikeGateway.like(articleId)) {
            throw new BizException("服务异常");
        }

        return ArticleBaseResponse.success();
    }

    @Override
    public ArticleBaseResponse<?> userHasLike(Long articleId) {
        if (articleLikeGateway.userHasLike(articleId)) {
            return ArticleBaseResponse.success(Map.of("like", true));
        }

        return ArticleBaseResponse.success(Map.of("like", false));
    }

    @Override
    public ArticleBaseResponse<?> getUserLikeArticles(int current, int size) {
        Page<ArticleLike> page = articleLikeGateway.selectList(current, size, new QueryWrapper<ArticleLike>().eq("user_id", ArticleContext.get().getId()));

        List<Long> articleIds = page.getRecords().stream().map(ArticleLike::getArticleId).collect(Collectors.toList());
        List<ArticleBasicInfo> articleBasicInfoList = articleIds.isEmpty() ? List.of() : articleBasicInfoGateway.selectByIdsList(articleIds);

        return ArticleBaseResponse.success(JSON.toJSONString(ArticleLikeVO.build(articleBasicInfoList, page)));
    }
}

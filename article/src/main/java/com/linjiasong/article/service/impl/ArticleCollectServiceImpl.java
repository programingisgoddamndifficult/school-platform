package com.linjiasong.article.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linjiasong.article.constant.ArticleContext;
import com.linjiasong.article.entity.ArticleBasicInfo;
import com.linjiasong.article.entity.ArticleCollect;
import com.linjiasong.article.entity.vo.ArticleCollectVO;
import com.linjiasong.article.excepiton.ArticleBaseResponse;
import com.linjiasong.article.excepiton.BizException;
import com.linjiasong.article.gateway.ArticleBasicInfoGateway;
import com.linjiasong.article.gateway.ArticleCollectGateway;
import com.linjiasong.article.mapper.ArticleCollectMapper;
import com.linjiasong.article.service.ArticleCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:09
 */
@Service
public class ArticleCollectServiceImpl extends ServiceImpl<ArticleCollectMapper, ArticleCollect> implements ArticleCollectService {

    @Autowired
    ArticleCollectGateway articleCollectGateway;

    @Autowired
    private ArticleBasicInfoGateway articleBasicInfoGateway;

    @Override
    public ArticleBaseResponse<?> collect(Long articleId) {
        if (!articleCollectGateway.collect(articleId)) {
            throw new BizException("服务异常");
        }

        return ArticleBaseResponse.success();
    }

    @Override
    public ArticleBaseResponse<?> userHasCollect(Long articleId) {
        if (articleCollectGateway.userHasCollect(articleId)) {
            return ArticleBaseResponse.success(Map.of("collect", true));
        }

        return ArticleBaseResponse.success(Map.of("collect", false));
    }

    @Override
    public ArticleBaseResponse<?> getUserCollectArticles(int current, int size) {
        Page<ArticleCollect> page = articleCollectGateway.selectList(current, size, new QueryWrapper<ArticleCollect>().eq("user_id", ArticleContext.get().getId()));

        List<Long> articleIds = page.getRecords().stream().map(ArticleCollect::getArticleId).collect(Collectors.toList());
        List<ArticleBasicInfo> articleBasicInfoList = articleIds.isEmpty() ? List.of() : articleBasicInfoGateway.selectByIdsList(articleIds);

        return ArticleBaseResponse.success(JSON.toJSONString(ArticleCollectVO.build(articleBasicInfoList, page)));
    }
}

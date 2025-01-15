package com.linjiasong.article.service.impl;

import com.linjiasong.article.constant.ArticleContext;
import com.linjiasong.article.entity.ArticleBasicInfo;
import com.linjiasong.article.entity.ArticleDetail;
import com.linjiasong.article.entity.dto.ArticleCreateDTO;
import com.linjiasong.article.entity.dto.ArticleUpdateDTO;
import com.linjiasong.article.entity.vo.ArticleBasicVo;
import com.linjiasong.article.excepiton.ArticleBaseResponse;
import com.linjiasong.article.excepiton.BizException;
import com.linjiasong.article.gateway.ArticleBasicInfoGateway;
import com.linjiasong.article.gateway.ArticleDetailGateway;
import com.linjiasong.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:39
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleBasicInfoGateway articleBasicInfoGateway;

    @Autowired
    ArticleDetailGateway articleDetailGateway;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleBaseResponse createArticle(ArticleCreateDTO articleCreateDTO) {
        if (articleCreateDTO.checkParam()) {
            throw new BizException("参数不合法");
        }

        Long userId = ArticleContext.get().getId();

        ArticleBasicInfo articleBasicInfo = ArticleBasicInfo.builder().userId(userId).articleTitle(articleCreateDTO.getTitle()).tag(articleCreateDTO.getTag()).build();
        if(!articleBasicInfoGateway.insert(articleBasicInfo)){
            throw new BizException("服务异常");
        }

        if(!articleDetailGateway.insert(ArticleDetail.builder().articleId(articleBasicInfo.getId()).content(articleCreateDTO.getContext()).imageUrl(ArticleDetail.toJsonImageUrl(articleCreateDTO.getImageUrl())).build())){
            throw new BizException("服务异常");
        }

        return ArticleBaseResponse.builder().code("200").msg("success").build();
    }

    @Override
    public ArticleBaseResponse getUserArticleBasic(Long userId) {
        List<ArticleBasicInfo> basicInfoList = articleBasicInfoGateway.getByUserId(userId);
        return ArticleBaseResponse.builder().code("200").msg("success").data(ArticleBasicVo.build(basicInfoList)).build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleBaseResponse updateArticle(ArticleUpdateDTO articleUpdateDTO) {
        Long userId = ArticleContext.get().getId();
        if(!userId.equals(articleUpdateDTO.getUserId())){
            throw new BizException("没有权限");
        }

        if (articleUpdateDTO.checkParam()) {
            throw new BizException("参数不合法");
        }

        Long articleId = articleUpdateDTO.getId();

        ArticleBasicInfo articleBasicInfo = ArticleBasicInfo.builder().id(articleId).articleTitle(articleUpdateDTO.getTitle())
                .tag(articleUpdateDTO.getTag()).updateTime(LocalDateTime.now()).build();
        if(!articleBasicInfoGateway.update(articleBasicInfo)){
            throw new BizException("服务异常");
        }

        ArticleDetail articleDetail = ArticleDetail.builder().articleId(articleId).content(articleUpdateDTO.getContext())
                .imageUrl(ArticleDetail.toJsonImageUrl(articleUpdateDTO.getImageUrl())).updateTime(LocalDateTime.now()).build();
        if(!articleDetailGateway.update(articleDetail)){
            throw new BizException("服务异常");
        }

        return ArticleBaseResponse.builder().code("200").msg("success").build();
    }
}

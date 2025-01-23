package com.linjiasong.article.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linjiasong.article.constant.ArticleContext;
import com.linjiasong.article.constant.RedisKeyEnum;
import com.linjiasong.article.constant.ThreadPoolContext;
import com.linjiasong.article.entity.ArticleBasicInfo;
import com.linjiasong.article.entity.ArticleDetail;
import com.linjiasong.article.entity.UserInfo;
import com.linjiasong.article.entity.dto.ArticleCheckDTO;
import com.linjiasong.article.entity.dto.ArticleCreateDTO;
import com.linjiasong.article.entity.dto.ArticleUpdateDTO;
import com.linjiasong.article.entity.vo.ArticleBasicVO;
import com.linjiasong.article.entity.vo.ArticleDetailVO;
import com.linjiasong.article.excepiton.ArticleBaseResponse;
import com.linjiasong.article.excepiton.BizException;
import com.linjiasong.article.gateway.ArticleBasicInfoGateway;
import com.linjiasong.article.gateway.ArticleDetailGateway;
import com.linjiasong.article.service.ArticleService;
import org.redisson.api.RList;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.protocol.ScoredEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    RedissonClient redissonClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleBaseResponse<?> createArticle(ArticleCreateDTO articleCreateDTO) {
        if (articleCreateDTO.checkParam()) {
            throw new BizException("参数不合法");
        }

        Long userId = ArticleContext.get().getId();

        ArticleBasicInfo articleBasicInfo = ArticleBasicInfo.builder().userId(userId).articleTitle(articleCreateDTO.getTitle()).tag(articleCreateDTO.getTag()).isOpen(articleCreateDTO.getIsOpen()).build();
        if (!articleBasicInfoGateway.insert(articleBasicInfo)) {
            throw new BizException("服务异常");
        }

        if (!articleDetailGateway.insert(ArticleDetail.builder().articleId(articleBasicInfo.getId()).content(articleCreateDTO.getContext()).imageUrl(ArticleDetail.toJsonImageUrl(articleCreateDTO.getImageUrl())).build())) {
            throw new BizException("服务异常");
        }

        createArticleToCheck(articleCreateDTO, articleBasicInfo.getId());

        return ArticleBaseResponse.builder().code("200").msg("success").build();
    }

    @Override
    public ArticleBaseResponse<?> getUserArticleBasic(Long userId) {
        List<ArticleBasicInfo> basicInfoList = articleBasicInfoGateway.getByUserId(userId);
        return ArticleBaseResponse.builder().code("200").msg("success").data(ArticleBasicVO.build(basicInfoList)).build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleBaseResponse<?> updateArticle(ArticleUpdateDTO articleUpdateDTO) {
        Long userId = ArticleContext.get().getId();
        if (!userId.equals(articleUpdateDTO.getUserId())) {
            throw new BizException("没有权限");
        }

        if (articleUpdateDTO.checkParam()) {
            throw new BizException("参数不合法");
        }

        Long articleId = articleUpdateDTO.getId();

        ArticleBasicInfo articleBasicInfo = ArticleBasicInfo.builder().id(articleId).articleTitle(articleUpdateDTO.getTitle())
                .tag(articleUpdateDTO.getTag()).updateTime(LocalDateTime.now()).build();
        if (!articleBasicInfoGateway.update(articleBasicInfo)) {
            throw new BizException("服务异常");
        }

        ArticleDetail articleDetail = ArticleDetail.builder().content(articleUpdateDTO.getContext())
                .imageUrl(ArticleDetail.toJsonImageUrl(articleUpdateDTO.getImageUrl())).updateTime(LocalDateTime.now()).build();
        if (!articleDetailGateway.update(articleDetail, new QueryWrapper<ArticleDetail>().eq("article_id", articleId))) {
            throw new BizException("服务异常");
        }

        return ArticleBaseResponse.builder().code("200").msg("success").build();
    }

    @Override
    public ArticleBaseResponse<?> deleteArticle(Long id) {
        if (!articleBasicInfoGateway.canDelete(id)) {
            throw new BizException("没有权限或文章不存在");
        }

        if (!articleBasicInfoGateway.deleteById(id)) {
            throw new BizException("服务异常");
        }

        return ArticleBaseResponse.builder().code("200").msg("success").build();
    }

    @Override
    public ArticleBaseResponse<?> getArticleDetail(Long articleId) {
        if (!articleBasicInfoGateway.isThisUserArticle(articleId)) {
            throw new BizException("没有权限或文章不存在");
        }

        ArticleBasicInfo articleBasicInfo = articleBasicInfoGateway.selectById(articleId);
        //如果非本人文章，则要判断是否已检查或封禁
        if (!articleBasicInfo.getUserId().equals(ArticleContext.get().getId())) {
            if (!articleBasicInfo.isCheck()) {
                throw new BizException("没有权限或文章不存在");
            }
            if (!articleBasicInfo.isBan()) {
                throw new BizException("没有权限或文章不存在");
            }
        }

        ArticleDetail articleDetail = articleDetailGateway.selectOne(new QueryWrapper<ArticleDetail>().eq("article_id", articleId));

        ThreadPoolContext.execute(() -> {
            long readNum = redissonClient.getAtomicLong(String.format(RedisKeyEnum.POINT_ARTICLE.getKey(), articleBasicInfo.getId())).get();
            articleBasicInfo.setReadNum(readNum);
            articleBasicInfoGateway.update(articleBasicInfo);
        });

        return ArticleBaseResponse.success(ArticleDetailVO.build(articleBasicInfo, articleDetail));
    }

    @Override
    public ArticleBaseResponse<?> openArticle(Long articleId) {
        if (!articleBasicInfoGateway.canOpen(articleId)) {
            throw new BizException("没有权限或文章不存在");
        }

        ArticleBasicInfo articleBasicInfo = articleBasicInfoGateway.selectById(articleId);
        if (articleBasicInfo.isOpen()) {
            articleBasicInfo.setIsOpen((short) 0);
        } else {
            articleBasicInfo.setIsOpen((short) 1);
        }

        if (!articleBasicInfoGateway.update(articleBasicInfo)) {
            throw new BizException("服务异常");
        }

        return ArticleBaseResponse.success();
    }

    @Override
    public ArticleBaseResponse<?> getHotArticle() {
        RScoredSortedSet<Long> scoredSortedSet = redissonClient.getScoredSortedSet(RedisKeyEnum.POINT_ARTICLE_SCORED.getKey());

        List<Long> articleIds = scoredSortedSet
                .entryRangeReversed(0, 150)
                .stream().limit(100).map(ScoredEntry::getValue).toList();

        return ArticleBaseResponse.success(ArticleBasicVO.build(articleBasicInfoGateway.selectByIdsList(articleIds).stream().filter(articleBasicInfo -> !articleBasicInfo.isBan()).toList()));
    }

    private void createArticleToCheck(ArticleCreateDTO articleDetailVO, Long id) {
        ThreadPoolContext.execute(() -> {
            RList<String> articleCheckList = redissonClient.getList(RedisKeyEnum.ARTICLE_CHECK_LIST.getKey());
            articleCheckList.add(JSON.toJSONString(ArticleCheckDTO.build(articleDetailVO, id)));
        });
    }
}

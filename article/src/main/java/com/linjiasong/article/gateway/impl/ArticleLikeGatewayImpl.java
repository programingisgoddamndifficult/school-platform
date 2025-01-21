package com.linjiasong.article.gateway.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linjiasong.article.constant.ArticleContext;
import com.linjiasong.article.entity.ArticleBasicInfo;
import com.linjiasong.article.entity.ArticleLike;
import com.linjiasong.article.excepiton.BizException;
import com.linjiasong.article.gateway.ArticleLikeGateway;
import com.linjiasong.article.mapper.ArticleBasicInfoMapper;
import com.linjiasong.article.mapper.ArticleLikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author linjiasong
 * @date 2025/1/21 上午11:02
 */
@Service
public class ArticleLikeGatewayImpl implements ArticleLikeGateway {

    @Autowired
    ArticleLikeMapper articleLikeMapper;

    @Autowired
    ArticleBasicInfoMapper articleBasicInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean like(Long articleId) {
        Long userId = ArticleContext.get().getId();

        ArticleBasicInfo articleInfo = getArticleBasicInfo(articleId);
        if (articleInfo.getUserId().equals(userId)) {
            throw new BizException("操作异常");
        }

        ArticleLike articleLike = articleLikeMapper.selectOne(new QueryWrapper<ArticleLike>().eq("article_id", articleId).eq("user_id", userId));

        if (articleLike == null) {
            updateLikeNum(articleInfo, articleInfo.getLikesNum() + 1);
            return doLike(articleId);
        }

        updateLikeNum(articleInfo, articleInfo.getLikesNum() - 1);
        return doUnlike(articleLike.getId());
    }

    private boolean doLike(Long articleId) {
        return articleLikeMapper.insert(ArticleLike.build(articleId, ArticleContext.get().getId())) > 0;
    }

    private boolean doUnlike(Long articleCollectId) {
        return articleLikeMapper.deleteById(articleCollectId) > 0;
    }

    private ArticleBasicInfo getArticleBasicInfo(Long articleId) {
        ArticleBasicInfo articleInfo = articleBasicInfoMapper.selectById(articleId);
        if (articleInfo == null) {
            throw new BizException("帖子不存在");
        }
        return articleInfo;
    }

    private void updateLikeNum(ArticleBasicInfo articleInfo, Long likeNum) {
        articleInfo.setLikesNum(likeNum);
        if (articleBasicInfoMapper.updateById(articleInfo) < 1) {
            throw new BizException("系统异常");
        }
    }
}

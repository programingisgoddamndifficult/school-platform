package com.linjiasong.article.gateway.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linjiasong.article.constant.ArticleContext;
import com.linjiasong.article.entity.ArticleBasicInfo;
import com.linjiasong.article.entity.ArticleCollect;
import com.linjiasong.article.excepiton.BizException;
import com.linjiasong.article.gateway.ArticleCollectGateway;
import com.linjiasong.article.mapper.ArticleBasicInfoMapper;
import com.linjiasong.article.mapper.ArticleCollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/20 下午5:57
 */
@Service
public class ArticleCollectGatewayImpl implements ArticleCollectGateway {

    @Autowired
    ArticleCollectMapper articleCollectMapper;

    @Autowired
    ArticleBasicInfoMapper articleBasicInfoMapper;

    @Override
    public boolean collect(Long articleId) {
        Long userId = ArticleContext.get().getId();

        ArticleBasicInfo articleInfo = getArticleBasicInfo(articleId);
        if (articleInfo.getUserId().equals(userId)) {
            throw new BizException("操作异常");
        }

        ArticleCollect articleCollect = articleCollectMapper.selectOne(new QueryWrapper<ArticleCollect>().eq("article_id", articleId).eq("user_id", userId));

        if(articleCollect == null){
            return doCollect(articleId);
        }

        return doUnCollect(articleCollect.getId());
    }

    private boolean doCollect(Long articleId){
        return articleCollectMapper.insert(ArticleCollect.build(articleId, ArticleContext.get().getId())) > 0;
    }

    private boolean doUnCollect(Long articleCollectId) {
        return articleCollectMapper.deleteById(articleCollectId) > 0;
    }

    private ArticleBasicInfo getArticleBasicInfo(Long articleId) {
        ArticleBasicInfo articleInfo = articleBasicInfoMapper.selectById(articleId);
        if (articleInfo == null) {
            throw new BizException("帖子不存在");
        }
        return articleInfo;
    }
}

package com.linjiasong.article.gateway.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linjiasong.article.constant.ArticleContext;
import com.linjiasong.article.entity.ArticleBasicInfo;
import com.linjiasong.article.excepiton.BizException;
import com.linjiasong.article.gateway.ArticleBasicInfoGateway;
import com.linjiasong.article.mapper.ArticleBasicInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:42
 */
@Service
public class ArticleBasicInfoGatewayImpl implements ArticleBasicInfoGateway {

    @Autowired
    ArticleBasicInfoMapper articleBasicInfoMapper;

    @Override
    public boolean insert(ArticleBasicInfo articleBasicInfo) {
        return articleBasicInfoMapper.insert(articleBasicInfo) > 0;
    }

    @Override
    public boolean update(ArticleBasicInfo articleBasicInfo) {
        return articleBasicInfoMapper.updateById(articleBasicInfo) > 0;
    }

    @Override
    public List<ArticleBasicInfo> getByUserId(Long userId) {
        return articleBasicInfoMapper.selectList(new QueryWrapper<ArticleBasicInfo>().eq("user_id", userId));
    }

    @Override
    public boolean deleteById(Long id) {
        return articleBasicInfoMapper.deleteById(id) > 0;
    }

    @Override
    public boolean isThisUserArticle(Long id) {
        Long userId = ArticleContext.get().getId();
        return articleBasicInfoMapper.selectOne(new QueryWrapper<ArticleBasicInfo>().eq("id", id).eq("user_id", userId)) != null;
    }
}

package com.linjiasong.article.gateway.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linjiasong.article.constant.ArticleContext;
import com.linjiasong.article.entity.ArticleBasicInfo;
import com.linjiasong.article.entity.dto.ArticlePageSelectDTO;
import com.linjiasong.article.excepiton.ArticleBaseResponse;
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
    public boolean update(UpdateWrapper<ArticleBasicInfo> updateWrapper) {
        return articleBasicInfoMapper.update(updateWrapper) > 0;
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
        ArticleBasicInfo articleBasicInfo = articleBasicInfoMapper.selectOne(new QueryWrapper<ArticleBasicInfo>().eq("id", id));
        if (articleBasicInfo == null) {
            return false;
        }

        if (articleBasicInfo.isOpen()) {
            return true;
        }

        Long userId = ArticleContext.get().getId();
        return userId.equals(articleBasicInfo.getUserId());
    }

    @Override
    public boolean canDelete(Long id) {
        Long userId = ArticleContext.get().getId();
        return articleBasicInfoMapper.selectOne(new QueryWrapper<ArticleBasicInfo>().eq("id", id).eq("user_id", userId)) != null;
    }

    @Override
    public ArticleBasicInfo selectById(Long id) {
        return articleBasicInfoMapper.selectById(id);
    }

    @Override
    public boolean deleteByUserId(Long userId) {
        return articleBasicInfoMapper.delete(new QueryWrapper<ArticleBasicInfo>().eq("user_id", userId)) > 0;
    }

    @Override
    public boolean canOpen(Long articleId) {
        Long userId = ArticleContext.get().getId();
        return articleBasicInfoMapper.selectOne(new QueryWrapper<ArticleBasicInfo>().eq("id", articleId).eq("user_id", userId)) != null;
    }

    @Override
    public List<ArticleBasicInfo> selectByIdsList(List<Long> ids) {
        return articleBasicInfoMapper.selectBatchIds(ids);
    }

    @Override
    public Page<ArticleBasicInfo> unRecommend(ArticlePageSelectDTO articlePageSelectDTO) {
        return articleBasicInfoMapper.selectPage(new Page<>(articlePageSelectDTO.getCurrent(),
                articlePageSelectDTO.getSize()), articlePageSelectDTO.buildQueryWrapper());
    }
}

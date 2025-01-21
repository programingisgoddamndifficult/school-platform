package com.linjiasong.article.gateway.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linjiasong.article.entity.ArticleComment;
import com.linjiasong.article.excepiton.BizException;
import com.linjiasong.article.gateway.ArticleCommentGateway;
import com.linjiasong.article.mapper.ArticleCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linjiasong
 * @date 2025/1/21 下午6:00
 */
@Service
public class ArticleCommentGatewayImpl implements ArticleCommentGateway {

    @Autowired
    ArticleCommentMapper articleCommentMapper;

    @Override
    public boolean comment(ArticleComment articleComment) {
        return articleCommentMapper.insert(articleComment) > 0;
    }

    @Override
    public boolean deleteComment(Long id) {
        return articleCommentMapper.deleteById(id) > 0;
    }

    @Override
    public ArticleComment selectOne(QueryWrapper<ArticleComment> queryWrapper) {
        return articleCommentMapper.selectOne(queryWrapper);
    }

    @Override
    public List<ArticleComment> selectList(QueryWrapper<ArticleComment> queryWrapper) {
        List<ArticleComment> articleComments = articleCommentMapper.selectList(queryWrapper);
        if(articleComments == null){
            return new ArrayList<>();
        }
        return articleComments;
    }
}

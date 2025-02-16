package com.linjiasong.article.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linjiasong.article.constant.ArticleContext;
import com.linjiasong.article.entity.ArticleComment;
import com.linjiasong.article.entity.dto.ArticleCommentDTO;
import com.linjiasong.article.entity.vo.ArticleCommentVO;
import com.linjiasong.article.excepiton.ArticleBaseResponse;
import com.linjiasong.article.excepiton.BizException;
import com.linjiasong.article.gateway.ArticleBasicInfoGateway;
import com.linjiasong.article.gateway.ArticleCommentGateway;
import com.linjiasong.article.mapper.ArticleCommentMapper;
import com.linjiasong.article.service.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:12
 */
@Service
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements ArticleCommentService {

    @Autowired
    private ArticleCommentGateway articleCommentGateway;

    @Autowired
    private ArticleBasicInfoGateway articleBasicInfoGateway;

    @Override
    public ArticleBaseResponse<?> comment(ArticleCommentDTO articleCommentDTO) {
        if (articleCommentDTO.getComment().length() > 100) {
            throw new BizException("评论内容长度不能超过100字");
        }

        if (!articleCommentGateway.comment(ArticleComment.build(articleCommentDTO))) {
            throw new BizException("服务异常");
        }
        return ArticleBaseResponse.success();
    }

    @Override
    public ArticleBaseResponse<?> deleteComment(Long id) {
        ArticleComment articleComment = articleCommentGateway.selectOne(new QueryWrapper<ArticleComment>()
                .eq("id", id).eq("user_id", ArticleContext.get().getId()));
        if (articleComment == null) {
            Long articleId = articleCommentGateway.selectOne(new QueryWrapper<ArticleComment>()
                    .eq("id", id)).getArticleId();
            Long userId = articleBasicInfoGateway.selectById(articleId).getUserId();
            //说明是自己的文章，那么可以随便删
            if (!userId.equals(ArticleContext.get().getId())) {
                throw new BizException("评论不存在或无权限");
            }
        }

        if (!articleCommentGateway.deleteComment(id)) {
            throw new BizException("服务异常");
        }

        return ArticleBaseResponse.success();
    }

    @Override
    public ArticleBaseResponse<?> getArticleComment(Long articleId) {
        List<ArticleComment> articleComments = articleCommentGateway.selectList(new QueryWrapper<ArticleComment>()
                .eq("article_id", articleId));
        return ArticleBaseResponse.success(JSON.toJSONString(ArticleCommentVO.build(articleComments)));
    }
}

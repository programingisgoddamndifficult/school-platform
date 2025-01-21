package com.linjiasong.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linjiasong.article.entity.ArticleComment;
import com.linjiasong.article.entity.dto.ArticleCommentDTO;
import com.linjiasong.article.excepiton.ArticleBaseResponse;
import com.linjiasong.article.excepiton.BizException;
import com.linjiasong.article.gateway.ArticleCommentGateway;
import com.linjiasong.article.mapper.ArticleCommentMapper;
import com.linjiasong.article.service.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:12
 */
@Service
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements ArticleCommentService {

    @Autowired
    private ArticleCommentGateway articleCommentGateway;

    @Override
    public ArticleBaseResponse comment(ArticleCommentDTO articleCommentDTO) {
        if(!articleCommentGateway.comment(ArticleComment.build(articleCommentDTO))){
            throw new BizException("服务异常");
        }
        return ArticleBaseResponse.success();
    }
}

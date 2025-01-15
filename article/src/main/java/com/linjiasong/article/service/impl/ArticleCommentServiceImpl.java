package com.linjiasong.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linjiasong.article.entity.ArticleComment;
import com.linjiasong.article.mapper.ArticleCommentMapper;
import com.linjiasong.article.service.ArticleCommentService;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:12
 */
@Service
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements ArticleCommentService {
}

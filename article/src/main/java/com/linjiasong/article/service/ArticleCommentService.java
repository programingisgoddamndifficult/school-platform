package com.linjiasong.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linjiasong.article.entity.ArticleComment;
import com.linjiasong.article.entity.dto.ArticleCommentDTO;
import com.linjiasong.article.excepiton.ArticleBaseResponse;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:11
 */
public interface ArticleCommentService extends IService<ArticleComment> {

    ArticleBaseResponse<?> comment(ArticleCommentDTO articleCommentDTO);

    ArticleBaseResponse<?> deleteComment(Long id);

    ArticleBaseResponse<?> getArticleComment(Long articleId);
}

package com.linjiasong.article.controller;

import com.linjiasong.article.entity.ArticleComment;
import com.linjiasong.article.entity.dto.ArticleCommentDTO;
import com.linjiasong.article.excepiton.ArticleBaseResponse;
import com.linjiasong.article.service.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author linjiasong
 * @date 2025/1/21 下午6:08
 */
@RestController
@RequestMapping("/api/article/comment")
public class ArticleCommentController {

    @Autowired
    private ArticleCommentService articleCommentService;

    @PostMapping
    public ArticleBaseResponse comment(@RequestBody ArticleCommentDTO articleCommentDTO) {
        return articleCommentService.comment(articleCommentDTO);
    }

    @PostMapping("/delete/{id}")
    public ArticleBaseResponse delete(@PathVariable("id") Long id) {
        return articleCommentService.deleteComment(id);
    }

}

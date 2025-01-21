package com.linjiasong.article.controller;

import com.linjiasong.article.excepiton.ArticleBaseResponse;
import com.linjiasong.article.service.ArticleLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linjiasong
 * @date 2025/1/21 上午11:08
 */
@RestController
@RequestMapping("/api/article/like")
public class ArticleLikeController {

    @Autowired
    private ArticleLikeService articleLikeService;

    @PostMapping("/{articleId}")
    public ArticleBaseResponse<?> like(@PathVariable("articleId") Long articleId) {
        return articleLikeService.like(articleId);
    }

}

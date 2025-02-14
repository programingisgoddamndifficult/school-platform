package com.linjiasong.article.controller;

import com.linjiasong.article.excepiton.ArticleBaseResponse;
import com.linjiasong.article.service.ArticleCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author linjiasong
 * @date 2025/1/20 下午5:54
 */

@RestController
@RequestMapping("/api/article/collect")
public class ArticleCollectController {

    @Autowired
    private ArticleCollectService articleCollectService;

    @PostMapping("/{articleId}")
    public ArticleBaseResponse<?> collect(@PathVariable("articleId") Long articleId) {
        return articleCollectService.collect(articleId);
    }

    @GetMapping
    public ArticleBaseResponse<?> userHasCollect(@RequestParam("articleId") Long articleId) {
        return articleCollectService.userHasCollect(articleId);
    }

}

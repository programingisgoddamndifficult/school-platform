package com.linjiasong.article.controller;

import com.linjiasong.article.excepiton.ArticleBaseResponse;
import com.linjiasong.article.service.ArticleCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linjiasong
 * @date 2025/1/20 下午5:54
 */

@RestController
@RequestMapping("/api/article/collect")
public class ArticleCollectController {

    @Autowired
    private ArticleCollectService articleCollectService;

    //TODO 收藏点赞都要更新basic表
    @PostMapping("/{articleId}")
    public ArticleBaseResponse collect(@PathVariable("articleId") Long articleId){
        return articleCollectService.collect(articleId);
    }

}

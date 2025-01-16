package com.linjiasong.article.controller;

import com.linjiasong.article.entity.dto.ArticleCreateDTO;
import com.linjiasong.article.entity.dto.ArticleUpdateDTO;
import com.linjiasong.article.excepiton.ArticleBaseResponse;
import com.linjiasong.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author linjiasong
 * @date 2025/1/15 下午6:08
 */

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ArticleBaseResponse createArticle(@RequestBody ArticleCreateDTO articleCreateDTO){
        return articleService.createArticle(articleCreateDTO);
    }

    @GetMapping("/basic")
    public ArticleBaseResponse getArticleBasicByUserId(@RequestParam("userId") Long userId){
        return articleService.getUserArticleBasic(userId);
    }

    @PostMapping("/update")
    public ArticleBaseResponse updateArticle(@RequestBody ArticleUpdateDTO articleUpdateDTO){
        return articleService.updateArticle(articleUpdateDTO);
    }

}

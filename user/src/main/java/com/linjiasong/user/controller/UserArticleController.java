package com.linjiasong.user.controller;

import com.linjiasong.user.entity.dto.ArticleCommentDTO;
import com.linjiasong.user.entity.dto.ArticleCreateDTO;
import com.linjiasong.user.entity.dto.ArticleUpdateDTO;
import com.linjiasong.user.excepiton.UserBaseResponse;
import com.linjiasong.user.service.UserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author linjiasong
 * @date 2025/1/21 下午2:21
 */
@RestController
@RequestMapping("/api/user/article")
public class UserArticleController {

    @Autowired
    UserArticleService userArticleService;

    @GetMapping
    public UserBaseResponse getUserArticle() {
        return userArticleService.getUserArticleBasic();
    }

    @PostMapping
    public UserBaseResponse createArticle(@RequestBody ArticleCreateDTO articleCreateDTO) {
        return userArticleService.createArticle(articleCreateDTO);
    }

    @PostMapping("/update")
    public UserBaseResponse updateArticle(@RequestBody ArticleUpdateDTO articleUpdateDTO) {
        return userArticleService.updateArticle(articleUpdateDTO);
    }

    @PostMapping("/delete/{id}")
    public UserBaseResponse deleteArticle(@PathVariable("id") Long id) {
        return userArticleService.deleteArticle(id);
    }

    @PostMapping("/collect/{articleId}")
    public UserBaseResponse collectArticle(@PathVariable("articleId") Long articleId) {
        return userArticleService.collect(articleId);
    }

    @PostMapping("/like/{articleId}")
    public UserBaseResponse likeArticle(@PathVariable("articleId") Long articleId) {
        return userArticleService.like(articleId);
    }

    @GetMapping("/detail")
    public UserBaseResponse getArticleDetail(@RequestParam("articleId") Long articleId) {
        return userArticleService.getArticleDetail(articleId);
    }

    @PostMapping("/comment")
    public UserBaseResponse comment(@RequestBody ArticleCommentDTO articleCommentDTO) {
        return userArticleService.comment(articleCommentDTO);
    }

    @PostMapping("/comment/delete/{id}")
    public UserBaseResponse deleteComment(@PathVariable("id") Long id) {
        return userArticleService.deleteComment(id);
    }

}

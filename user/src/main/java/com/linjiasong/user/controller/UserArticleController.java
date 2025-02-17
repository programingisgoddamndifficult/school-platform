package com.linjiasong.user.controller;

import com.linjiasong.user.entity.dto.*;
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
    public UserBaseResponse<?> getUserArticle(@RequestParam("current") int current, @RequestParam("size") int size) {
        return userArticleService.getUserArticleBasic(current, size);
    }

    @PostMapping
    public UserBaseResponse<?> createArticle(@RequestBody ArticleCreateDTO articleCreateDTO) {
        return userArticleService.createArticle(articleCreateDTO);
    }

    @PostMapping("/update")
    public UserBaseResponse<?> updateArticle(@RequestBody ArticleUpdateDTO articleUpdateDTO) {
        return userArticleService.updateArticle(articleUpdateDTO);
    }

    @PostMapping("/delete/{id}")
    public UserBaseResponse<?> deleteArticle(@PathVariable("id") Long id) {
        return userArticleService.deleteArticle(id);
    }

    @PostMapping("/collect/{articleId}")
    public UserBaseResponse<?> collectArticle(@PathVariable("articleId") Long articleId) {
        return userArticleService.collect(articleId);
    }

    @GetMapping("/collect")
    public UserBaseResponse<?> userHasCollect(@RequestParam("articleId") Long articleId) {
        return userArticleService.userHasCollect(articleId);
    }

    @GetMapping("/collect/list")
    public UserBaseResponse<?> getUserCollectArticles(@RequestParam("current") int current, @RequestParam("size") int size) {
        return userArticleService.getUserCollectArticles(current, size);
    }

    @PostMapping("/like/{articleId}")
    public UserBaseResponse<?> likeArticle(@PathVariable("articleId") Long articleId) {
        return userArticleService.like(articleId);
    }

    @GetMapping("/like")
    public UserBaseResponse<?> userHasLike(@RequestParam("articleId") Long articleId) {
        return userArticleService.userHasLike(articleId);
    }

    @GetMapping("/like/list")
    public UserBaseResponse<?> getUserLikeArticles(@RequestParam("current") int current, @RequestParam("size") int size) {
        return userArticleService.getUserLikeArticles(current, size);
    }

    @GetMapping("/detail")
    public UserBaseResponse<?> getArticleDetail(@RequestParam("articleId") Long articleId) {
        return userArticleService.getArticleDetail(articleId);
    }

    @GetMapping("/detail/self")
    public UserBaseResponse<?> getSelfArticleDetail(@RequestParam("articleId") Long articleId) {
        return userArticleService.getSelfArticleDetail(articleId);
    }

    @PostMapping("/comment")
    public UserBaseResponse<?> comment(@RequestBody ArticleCommentDTO articleCommentDTO) {
        return userArticleService.comment(articleCommentDTO);
    }

    @PostMapping("/comment/delete/{id}")
    public UserBaseResponse<?> deleteComment(@PathVariable("id") Long id) {
        return userArticleService.deleteComment(id);
    }

    @GetMapping("/comment/{articleId}")
    public UserBaseResponse<?> getArticleComments(@PathVariable("articleId") Long articleId) {
        return userArticleService.getArticleComments(articleId);
    }

    @PostMapping("/open/{articleId}")
    public UserBaseResponse<?> openArticle(@PathVariable("articleId") Long articleId) {
        return userArticleService.openArticle(articleId);
    }

    @GetMapping("/hot")
    public UserBaseResponse<?> getArticleHot() {
        return userArticleService.hotArticle();
    }

    @GetMapping("/selfList")
    public UserBaseResponse<?> getUserWatchArticleList() {
        return userArticleService.getArticleUserWatchList();
    }

    @PostMapping("/selfList/delete")
    public UserBaseResponse<?> deleteUserWatchArticleList(@RequestBody ArticleDeleteUserWatchDTO articleDeleteUserWatchDTO) {
        return userArticleService.deleteUserWatch(articleDeleteUserWatchDTO);
    }

    @PostMapping("/index")
    public UserBaseResponse<?> getIndexArticle(@RequestBody ArticlePageSelectDTO articlePageSelectDTO) {
        return userArticleService.getArticleList(articlePageSelectDTO);
    }
}

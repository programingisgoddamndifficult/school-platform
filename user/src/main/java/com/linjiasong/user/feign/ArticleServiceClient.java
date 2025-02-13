package com.linjiasong.user.feign;

import com.linjiasong.user.config.FeignConfiguration;
import com.linjiasong.user.entity.dto.*;
import com.linjiasong.user.excepiton.UserBaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author linjiasong
 * @date 2025/01/15 21:14
 */

@FeignClient(value = "article-service", configuration = FeignConfiguration.class)
public interface ArticleServiceClient {

    @GetMapping("/api/article/basic")
    UserBaseResponse<?> getArticleBasicByUserId(@RequestParam("userId") Long userId);

    @PostMapping("/api/article")
    UserBaseResponse<?> createArticle(@RequestBody ArticleCreateDTO articleCreateDTO);

    @PostMapping("/api/article/update")
    UserBaseResponse<?> updateArticle(@RequestBody ArticleUpdateDTO articleUpdateDTO);

    @PostMapping("/api/article/delete/{id}")
    UserBaseResponse<?> deleteArticle(@PathVariable("id") Long id);

    @PostMapping("/api/article/collect/{articleId}")
    UserBaseResponse<?> collect(@PathVariable("articleId") Long articleId);

    @PostMapping("/api/article/like/{articleId}")
    UserBaseResponse<?> like(@PathVariable("articleId") Long articleId);

    @GetMapping("/api/article/detail")
    UserBaseResponse<?> getArticleDetail(@RequestParam("articleId") Long articleId);

    @PostMapping("/api/article/comment")
    UserBaseResponse<?> comment(@RequestBody ArticleCommentDTO articleCommentDTO);

    @PostMapping("/api/article/comment/delete/{id}")
    UserBaseResponse<?> deleteComment(@PathVariable("id") Long id);

    @GetMapping("/api/article/comment/{articleId}")
    UserBaseResponse<?> getArticleComments(@PathVariable("articleId") Long articleId);

    @PostMapping("/api/article/open/{articleId}")
    UserBaseResponse<?> openArticle(@PathVariable("articleId") Long articleId);

    @GetMapping("/api/article/hot")
    UserBaseResponse<?> hotArticle();

    @GetMapping("/api/article/selfList")
    UserBaseResponse<?> getUserWatchArticleList();

    @PostMapping("/api/article/selfList/delete")
    UserBaseResponse<?> deleteUserWatch(ArticleDeleteUserWatchDTO articleDeleteUserWatchDTO);

    @PostMapping("/api/article/index")
    UserBaseResponse<?> getIndexArticle(@RequestBody ArticlePageSelectDTO articlePageSelectDTO);

    @PostMapping("/api/article/ai/chat")
    UserBaseResponse<?> chat(@RequestBody QianWenChatDTO qianWenChatDTO);

    @PostMapping("/api/article/ai/sum/{articleId}")
    UserBaseResponse<?> summaryArticle(@PathVariable("articleId") Long articleId);
}

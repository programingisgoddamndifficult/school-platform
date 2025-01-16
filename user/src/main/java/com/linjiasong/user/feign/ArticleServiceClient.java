package com.linjiasong.user.feign;

import com.linjiasong.user.config.FeignConfiguration;
import com.linjiasong.user.entity.dto.ArticleCreateDTO;
import com.linjiasong.user.entity.dto.ArticleUpdateDTO;
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
    UserBaseResponse getArticleBasicByUserId(@RequestParam("userId") Long userId);

    @PostMapping("/api/article")
    UserBaseResponse createArticle(@RequestBody ArticleCreateDTO articleCreateDTO);

    @PostMapping("/api/article/update")
    UserBaseResponse updateArticle(@RequestBody ArticleUpdateDTO articleUpdateDTO);
}

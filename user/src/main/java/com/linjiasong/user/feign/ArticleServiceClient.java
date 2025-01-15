package com.linjiasong.user.feign;

import com.linjiasong.user.excepiton.UserBaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author linjiasong
 * @date 2025/01/15 21:14
 */

@FeignClient("article-service")
public interface ArticleServiceClient {

    @GetMapping("/api/article/basic")
    UserBaseResponse getArticleBasicByUserId(@RequestParam("userId") Long userId);
}

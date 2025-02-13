package com.linjiasong.user.controller;

import com.linjiasong.user.entity.dto.QianWenChatDTO;
import com.linjiasong.user.excepiton.UserBaseResponse;
import com.linjiasong.user.feign.ArticleServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author linjiasong
 * @date 2025/2/13 下午2:19
 */
@RestController
@RequestMapping("/api/user/ai")
public class QianWenAiController {

    @Autowired
    ArticleServiceClient articleServiceClient;

    @PostMapping("/chat")
    public UserBaseResponse<?> chat(@RequestBody QianWenChatDTO qianWenChatDTO) {
        return articleServiceClient.chat(qianWenChatDTO);
    }

    @PostMapping("/sum/{articleId}")
    public UserBaseResponse<?> articleSummary(@PathVariable("articleId") Long articleId) {
        return articleServiceClient.summaryArticle(articleId);
    }

}

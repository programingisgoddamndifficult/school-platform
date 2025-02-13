package com.linjiasong.article.controller;

import com.linjiasong.article.entity.dto.QianWenChatDTO;
import com.linjiasong.article.excepiton.ArticleBaseResponse;
import com.linjiasong.article.service.QianWenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linjiasong
 * @date 2025/2/13 下午2:07
 */

@RestController
@RequestMapping("/api/article/ai")
public class ArticleQianWenController {

    @Autowired
    QianWenService qianWenService;

    @PostMapping("/chat")
    public ArticleBaseResponse<?> chat(@RequestBody QianWenChatDTO qianWenChatDTO) {
        return qianWenService.chat(qianWenChatDTO);
    }

}

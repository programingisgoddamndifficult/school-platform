package com.linjiasong.article.handler;

import com.linjiasong.article.excepiton.ArticleBaseResponse;
import com.linjiasong.article.excepiton.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author linjiasong
 * @date 2025/1/17 上午11:13
 */
@RestControllerAdvice
@Slf4j
public class ArticleExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = BizException.class)
    public ArticleBaseResponse handleBizException(BizException e) {
        return ArticleBaseResponse.builder().msg(e.getMessage()).code("400").build();
    }

}

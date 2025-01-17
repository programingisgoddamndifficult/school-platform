package com.linjiasong.admin.handler;

import com.linjiasong.admin.excepiton.AdminBaseResponse;
import com.linjiasong.admin.excepiton.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author linjiasong
 * @date 2025/1/17 下午2:29
 */
@RestControllerAdvice
@Slf4j
public class AdminExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = BizException.class)
    public AdminBaseResponse handleBizException(BizException e) {
        return AdminBaseResponse.builder().msg(e.getMessage()).code("400").build();
    }
}

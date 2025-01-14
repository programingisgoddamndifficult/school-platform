package com.linjiasong.user.handler;

import com.linjiasong.user.excepiton.BizException;
import com.linjiasong.user.excepiton.UserBaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author linjiasong
 * @date 2025/1/13 下午5:16
 */
@RestControllerAdvice
@Slf4j
public class UserExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = BizException.class)
    public UserBaseResponse handleBizException(BizException e) {
        return UserBaseResponse.builder().msg(e.getMessage()).code("400").build();
    }
}

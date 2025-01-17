package com.linjiasong.admin.excepiton;

/**
 * @author linjiasong
 * @date 2025/1/17 下午2:28
 */
public class ExceptionFactory  {

    public static BizException bizException(String errorMessage) {
        return new BizException(errorMessage);
    }

    public static BizException bizException(String errorCode, String errorMessage) {
        return new BizException(errorCode, errorMessage);
    }

}

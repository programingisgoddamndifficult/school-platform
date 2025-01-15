package com.linjiasong.article.excepiton;

/**
 * @author linjiasong
 * @date 2025/1/13 下午5:10
 */
public class ExceptionFactory  {

    public static BizException bizException(String errorMessage) {
        return new BizException(errorMessage);
    }

    public static BizException bizException(String errorCode, String errorMessage) {
        return new BizException(errorCode, errorMessage);
    }

}

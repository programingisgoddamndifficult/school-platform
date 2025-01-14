package com.linjiasong.user.excepiton;

/**
 * @author linjiasong
 * @date 2025/1/13 下午5:10
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String code;

    public BaseException(String errMessage) {
        super(errMessage);
    }

    public BaseException(String code, String errMessage) {
        super(errMessage);
        this.code = code;
    }

    public BaseException(String errMessage, Throwable e) {
        super(errMessage, e);
    }

    public BaseException(String code, String errMessage, Throwable e) {
        super(errMessage, e);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
package com.linjiasong.user.excepiton;

/**
 * @author linjiasong
 * @date 2025/1/13 下午5:10
 */
public class BizException extends BaseException {

    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_ERR_CODE = ErrorCodeConstants.DEFAULT_ERR_CODE;

    public BizException(String errMessage) {
        super(DEFAULT_ERR_CODE, errMessage);
    }

    public BizException(Object errMessage){
        this(errMessage.toString());
    }

    public BizException(String templateErrMessage, Object... args) {
        super(DEFAULT_ERR_CODE, String.format(templateErrMessage, args));
    }

    public BizException(String errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public BizException(String errMessage, Throwable e) {
        super(DEFAULT_ERR_CODE, errMessage, e);
    }

    public BizException(String errorCode, String errMessage, Throwable e) {
        super(errorCode, errMessage, e);
    }

}

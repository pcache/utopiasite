package com.nuoyu.utopia.utopiasso.component.exception;

/**
 * utopia异常类 其余继承这个类
 * Created by Administrator on 2015/1/13.
 */
public class UtopiaException extends Exception {

    /*
    存在返回页面的异常信息
     */
    private UtopiaExceptionMessage utopiaMessage;

    /**
     * 构造函数
     *
     * @param errorCode 错误编码
     */
    public UtopiaException(UtopiaErrorDefinition errorCode) {
        super(errorCode.getMessage());
        utopiaMessage = new UtopiaExceptionMessage(errorCode.getErrorCode(),errorCode.getMessage());
    }

    public UtopiaExceptionMessage getUtopiaMessage() {
        return utopiaMessage;
    }

    public void setUtopiaMessage(UtopiaExceptionMessage utopiaMessage) {
        this.utopiaMessage = utopiaMessage;
    }
}

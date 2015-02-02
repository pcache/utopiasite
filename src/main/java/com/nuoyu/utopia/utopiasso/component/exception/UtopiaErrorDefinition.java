package com.nuoyu.utopia.utopiasso.component.exception;

/**
 * Created by liuxin3 on 2015/1/15.
 * 定义异常信息
 */
public enum UtopiaErrorDefinition{
    // 定义异常信息 1开头为系统异常 如权限校验等，2开头为业务异常
    PERMISSION("10000","权限相关异常"),BUSINESS_NO_USER("20001","没有这个用户"),UNDEFINITION("00000","系统未知异常，请联系xxx");

    // 错误码
    private String errorCode;
    // 错误信息
    private String message;

    UtopiaErrorDefinition(String code, String message) {
        this.errorCode = code;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.nuoyu.utopia.utopiasso.component.exception;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.io.Serializable;

/**
 * Created by liuxin3 on 2015/1/14.
 * 异常信息类 存放异常信息
 */
@XStreamAlias("error")
public class UtopiaExceptionMessage  implements Serializable {

    @XStreamAsAttribute
    private String code = "";
    @XStreamAsAttribute
    private String message = "";

    public UtopiaExceptionMessage() {
    }

    public UtopiaExceptionMessage(UtopiaErrorDefinition errorDefinition) {
        this.code = errorDefinition.getErrorCode();
        this.message = errorDefinition.getMessage();
    }

    public UtopiaExceptionMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

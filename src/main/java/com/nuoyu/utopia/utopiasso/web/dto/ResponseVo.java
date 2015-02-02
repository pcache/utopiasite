package com.nuoyu.utopia.utopiasso.web.dto;

import com.nuoyu.utopia.utopiasso.component.exception.UtopiaExceptionMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liuxin3 on 2015/1/14.
 */
@XStreamAlias("result")
public class ResponseVo{
    // 状态码 0失败 1成功
    @XStreamAsAttribute
    private String status = "1";
    // 链接
    @XStreamAsAttribute
    private String link;
    // 返回业务数据
    @XStreamAsAttribute
    private List<BaseDto> data;

    // 异常信息
    private UtopiaExceptionMessage error = new UtopiaExceptionMessage();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<BaseDto> getData() {
        return data;
    }

    public void setData(List<BaseDto> data) {
        this.data = data;
    }

    public UtopiaExceptionMessage getError() {
        return error;
    }

    public void setError(UtopiaExceptionMessage error) {
        this.error = error;
    }
}

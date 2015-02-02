package com.nuoyu.utopia.utopiasso.web.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Created by liuxin3 on 2015/1/12.
 */
public class BaseCommand{

    /*
    任性的测试下
     */
    private String test;

    /*
    httprequest
     */
    private HttpServletRequest request;

    /*
   httpresponse
    */
    private HttpServletResponse response;

    /*
     请求IP
     */
    private String ip;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}

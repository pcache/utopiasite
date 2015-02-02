package com.nuoyu.utopia.utopiasso.common.context;

/**
 * Created by liuxin3 on 2015/1/9.
 * utopia 常量
 */
public class UtopiaConstants {

    // 应用名称
    public static final String APPLICATION_NAME = "UTOPIASSO";
    // web.xml中加载指定文件名的日志配置文件
    public static final String LOG_CONFIG_LOCATION = "logbackConfigLocation";
    //request
    public static final String UTOPIA_CONTEXT_REQUEST = "request";
    // response
    public static final String UTOPIA_CONTEXT_RESPONSE = "response";
    //request级编码UID的KEY值
    public static String utopiaRequestUid;

    public void setUtopiaRequestUid(String utopiaRequestUid) {
        this.utopiaRequestUid = utopiaRequestUid;
    }

    public String getUtopiaRequestUid() {
        return utopiaRequestUid;
    }
}

package com.nuoyu.utopia.utopiasso.common.utils;

import com.nuoyu.utopia.utopiasso.common.context.UtopiaConstants;
import com.nuoyu.utopia.utopiasso.common.context.UtopiaTheadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by liuxin3 on 2015/1/22.
 * cookie工具类
 */
public class CookieUtil {

    private static final Logger log = LoggerFactory.getLogger(CookieUtil.class);

    /**
     * 得到Cookie的值, 不编码
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request,
                                        String cookieName) {
        return getCookieValue(request, cookieName, false);
    }

    /**
     * 得到Cookie的值, 不编码 默认从本地线程获取
     *
     * @param cookieName
     * @return
     */
    public static String getCookieValue(String cookieName) {
        return getCookieValue((HttpServletRequest) UtopiaTheadLocal.getParamFromLocal(UtopiaConstants.UTOPIA_CONTEXT_REQUEST), cookieName);
    }

    /**
     * 得到Cookie的值,可以选择是否以UTF-8形式编码
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request,
                                        String cookieName, boolean isDecoder) {
        Cookie cookieList[] = request.getCookies();
        if (cookieList == null || cookieName == null)
            return null;
        String retValue = null;
        try {
            for (int i = 0; i < cookieList.length; i++) {
                if (cookieList[i].getName().equals(cookieName)) {
                    if (isDecoder) {
                        retValue = URLDecoder.decode(cookieList[i].getValue(),
                                "utf-8");
                    } else {
                        retValue = cookieList[i].getValue();
                    }
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            log.error("Cookie Decode Error.", e);
        }
        return retValue;
    }

    /**
     * 得到Cookie的值,可以选择是否以UTF-8形式编码 默认从本地线程取
     *
     * @param cookieName
     * @param isDecoder  如果TRUE则按UTF-8编码
     * @return
     */
    public static String getCookieValue(String cookieName, boolean isDecoder) {
        return getCookieValue((HttpServletRequest) UtopiaTheadLocal.getParamFromLocal(UtopiaConstants.UTOPIA_CONTEXT_REQUEST), cookieName, isDecoder);
    }

    /**
     * 得到Cookie的值,可以选择编码方式
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request,
                                        String cookieName, String encodeString) {
        Cookie cookieList[] = request.getCookies();
        if (cookieList == null || cookieName == null)
            return null;
        String retValue = null;
        try {
            for (int i = 0; i < cookieList.length; i++) {
                if (cookieList[i].getName().equals(cookieName)) {

                    retValue = URLDecoder.decode(cookieList[i].getValue(),
                            encodeString);

                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            log.error("Cookie Decode Error.", e);
        }
        return retValue;
    }

    /**
     * 得到Cookie的值,可以选择编码方式 默认request从ThroadLocal中获取
     *
     * @param cookieName
     * @param encodeString
     * @return
     */
    public static String getCookieValue(String cookieName, String encodeString) {
        return getCookieValue((HttpServletRequest) UtopiaTheadLocal.getParamFromLocal(UtopiaConstants.UTOPIA_CONTEXT_REQUEST), cookieName, encodeString);
    }

    /**
     * 设置Cookie的值 不设置生效时间默认浏览器关闭即失效,也不编码
     */
    public static void setCookie(HttpServletRequest request,
                                 HttpServletResponse response, String cookieName, String cookieValue) {
        setCookie(request, response, cookieName, cookieValue, -1);
    }

    /**
     * 设置Cookie的值 不设置生效时间默认浏览器关闭即失效,也不编码 默认从本地线程取
     */
    public static void setCookie(String cookieName, String cookieValue) {
        setCookie((HttpServletRequest) UtopiaTheadLocal.getParamFromLocal(UtopiaConstants.UTOPIA_CONTEXT_REQUEST),
                (HttpServletResponse) UtopiaTheadLocal.getParamFromLocal(UtopiaConstants.UTOPIA_CONTEXT_RESPONSE)
                , cookieName, cookieValue);
    }

    /**
     * 设置Cookie的值 在指定时间内生效,但不编码
     */
    public static void setCookie(HttpServletRequest request,
                                 HttpServletResponse response, String cookieName,
                                 String cookieValue, int cookieMaxage) {
        setCookie(request, response, cookieName, cookieValue, cookieMaxage,
                false);
    }

    /**
     * 设置Cookie的值 不设置生效时间,但编码
     */
    public static void setCookie(HttpServletRequest request,
                                 HttpServletResponse response, String cookieName,
                                 String cookieValue, boolean isEncode) {
        setCookie(request, response, cookieName, cookieValue, -1, isEncode);
    }

    /**
     * 设置Cookie的值 不设置生效时间,但编码
     *
     * @param cookieName
     * @param cookieValue
     * @param isEncode    if true encode By utf-8
     */
    public static void setCookie(String cookieName, String cookieValue, boolean isEncode) {
        setCookie((HttpServletRequest) UtopiaTheadLocal.getParamFromLocal(UtopiaConstants.UTOPIA_CONTEXT_REQUEST),
                (HttpServletResponse) UtopiaTheadLocal.getParamFromLocal(UtopiaConstants.UTOPIA_CONTEXT_RESPONSE)
                , cookieName, cookieValue, isEncode);
    }

    /**
     * 设置Cookie的值 在指定时间内生效, 编码参数
     */
    public static void setCookie(HttpServletRequest request,
                                 HttpServletResponse response, String cookieName,
                                 String cookieValue, int cookieMaxage, boolean isEncode) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage,
                isEncode);
    }

    /**
     * 设置Cookie的值 在指定时间内生效, 编码参数
     */
    public static void setCookie(String cookieName, String cookieValue, int cookieMaxage, boolean isEncode) {
        doSetCookie((HttpServletRequest) UtopiaTheadLocal.getParamFromLocal(UtopiaConstants.UTOPIA_CONTEXT_REQUEST),
                (HttpServletResponse) UtopiaTheadLocal.getParamFromLocal(UtopiaConstants.UTOPIA_CONTEXT_RESPONSE)
                , cookieName, cookieValue, cookieMaxage,
                isEncode);
    }

    /**
     * 设置Cookie的值 在指定时间内生效, 编码参数(指定编码)
     */
    public static void setCookie(HttpServletRequest request,
                                 HttpServletResponse response, String cookieName,
                                 String cookieValue, int cookieMaxage, String encodeString) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage,
                encodeString);
    }

    /**
     * 设置Cookie的值 在指定时间内生效, 编码参数(指定编码)
     */
    public static void setCookie(String cookieName, String cookieValue, int cookieMaxage, String encodeString) {
        doSetCookie((HttpServletRequest) UtopiaTheadLocal.getParamFromLocal(UtopiaConstants.UTOPIA_CONTEXT_REQUEST),
                (HttpServletResponse) UtopiaTheadLocal.getParamFromLocal(UtopiaConstants.UTOPIA_CONTEXT_RESPONSE)
                , cookieName, cookieValue, cookieMaxage, encodeString);
    }

    /**
     * 删除Cookie带cookie域名
     */
    public static void deleteCookie(HttpServletRequest request,
                                    HttpServletResponse response, String cookieName) {
        doSetCookie(request, response, cookieName, "", -1, false);
    }

    /**
     * 删除Cookie带cookie域名
     */
    public static void deleteCookie(String cookieName) {
        doSetCookie((HttpServletRequest) UtopiaTheadLocal.getParamFromLocal(UtopiaConstants.UTOPIA_CONTEXT_REQUEST),
                (HttpServletResponse) UtopiaTheadLocal.getParamFromLocal(UtopiaConstants.UTOPIA_CONTEXT_RESPONSE)
                , cookieName, "", -1, false);
    }

    /**
     * 设置Cookie的值，并使其在指定时间内生效
     *
     * @param cookieMaxage cookie生效的最大秒数
     */
    private static final void doSetCookie(HttpServletRequest request,
                                          HttpServletResponse response, String cookieName,
                                          String cookieValue, int cookieMaxage, boolean isEncode) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else if (isEncode) {
                cookieValue = URLEncoder.encode(cookieValue, "utf-8");
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0)
                cookie.setMaxAge(cookieMaxage);
            if (null != request)// 设置域名的cookie
                cookie.setDomain(getDomainName(request));
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            log.error("Cookie Encode Error.", e);
        }
    }

    /**
     * 设置Cookie的值，并使其在指定时间内生效
     *
     * @param cookieMaxage cookie生效的最大秒数
     */
    private static final void doSetCookie(HttpServletRequest request,
                                          HttpServletResponse response, String cookieName,
                                          String cookieValue, int cookieMaxage, String encodeString) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else {
                cookieValue = URLEncoder.encode(cookieValue, encodeString);
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0)
                cookie.setMaxAge(cookieMaxage);
            if (null != request)// 设置域名的cookie
                cookie.setDomain(getDomainName(request));
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            log.error("Cookie Encode Error.", e);
        }
    }

    /**
     * 得到cookie的域名
     */
    private static final String getDomainName(HttpServletRequest request) {
        String domainName = null;

        String serverName = request.getRequestURL().toString();
        if (serverName == null || serverName.equals("")) {
            domainName = "";
        } else {
            serverName = serverName.toLowerCase();
            serverName = serverName.substring(7);
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0, end);
            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if (len > 3) {
                // www.xxx.com.cn
                domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            } else if (len <= 3 && len > 1) {
                // xxx.com or xxx.cn
                domainName = "." + domains[len - 2] + "." + domains[len - 1];
            } else {
                domainName = serverName;
            }
        }

        if (domainName != null && domainName.indexOf(":") > 0) {
            String[] ary = domainName.split("\\:");
            domainName = ary[0];
        }
        return domainName;
    }
}

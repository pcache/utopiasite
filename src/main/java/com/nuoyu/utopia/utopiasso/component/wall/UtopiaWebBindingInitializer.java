package com.nuoyu.utopia.utopiasso.component.wall;

import com.nuoyu.utopia.utopiasso.common.context.UtopiaConstants;
import com.nuoyu.utopia.utopiasso.common.context.UtopiaTheadLocal;
import com.nuoyu.utopia.utopiasso.web.cmd.BaseCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2015/1/8.
 * 自定义springMVC的属性编辑器 初始化一些参数
 */
public class UtopiaWebBindingInitializer implements WebBindingInitializer {

    private static final Logger log = LoggerFactory.getLogger(UtopiaWebBindingInitializer.class);

    @Override
    public void initBinder(WebDataBinder webDataBinder, WebRequest webRequest) {
       /** 如果controller返回页面的也是baseCommand 那么可以通过这种方式*/
        Object o =webDataBinder.getTarget();
        if(o instanceof BaseCommand){
            log.info("初始化请求参数:::start");
            BaseCommand cmd = (BaseCommand)o ;
            //设置IP  从utopiaLocal取request
            cmd.setRequest((HttpServletRequest) UtopiaTheadLocal.getParamFromLocal(UtopiaConstants.UTOPIA_CONTEXT_REQUEST));
            cmd.setResponse((HttpServletResponse) UtopiaTheadLocal.getParamFromLocal(UtopiaConstants.UTOPIA_CONTEXT_RESPONSE));
            cmd.setIp(getClientIpWithProxy(cmd.getRequest()));
            log.info("初始化请求参数:::end request ip:" + cmd.getIp());
        }

    }

    /**
     * 获得请求IP
     * @param request
     * @return
     */
    private String getClientIpWithProxy(HttpServletRequest request) {
        String ipAddress = "";

        ipAddress = request.getHeader("X-Real-IP");

        if ((!StringUtils.hasText(ipAddress)) || ("unknown".equalsIgnoreCase(ipAddress))) {
            ipAddress = request.getHeader("x-forwarded-for");
        }

        if ((!StringUtils.hasText(ipAddress)) || ("unknown".equalsIgnoreCase(ipAddress))) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if ((!StringUtils.hasText(ipAddress)) || ("unknown".equalsIgnoreCase(ipAddress))) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((!StringUtils.hasText(ipAddress)) || ("unknown".equalsIgnoreCase(ipAddress))) {
            ipAddress = request.getRemoteAddr();
        }

        if ((ipAddress != null) && (ipAddress.length() > 15) && (ipAddress.indexOf(",") > 0)) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        }

        return ipAddress;
    }
}

package com.nuoyu.utopia.utopiasso.component.exception.resolver;

import com.nuoyu.utopia.utopiasso.component.exception.UtopiaErrorDefinition;
import com.nuoyu.utopia.utopiasso.component.exception.UtopiaException;
import com.nuoyu.utopia.utopiasso.component.exception.UtopiaExceptionMessage;
import com.nuoyu.utopia.utopiasso.web.controller.ExampleController;
import com.nuoyu.utopia.utopiasso.web.dto.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理  处理未定义的异常  对于已定义的异常   封装个自定义异常 定义date存储异常数据
 * 根据不同的请求判断返回数据类型 XML JSON VELOCATIY
 * Created by Administrator on 2015/1/13.*/

public class UtopiaExceptionResolver implements HandlerExceptionResolver {

    private static final Logger log = LoggerFactory.getLogger(UtopiaExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        UtopiaExceptionMessage utopiaMessage = null;
        // 如果是定义的异常  读取异常内容
        if(e instanceof UtopiaException){
            //考虑返回的状态码 500？ 200？
            utopiaMessage = ((UtopiaException) e).getUtopiaMessage();
            log.warn("UtopiaException====>code:" + utopiaMessage.getCode()+"::::message===>"+utopiaMessage.getMessage());
        }else{
            // 如果是未定义的异常  设置状态码 500 暂定
            response.setStatus(500);
            utopiaMessage = new UtopiaExceptionMessage(UtopiaErrorDefinition.UNDEFINITION);
            log.error("未知系统异常:::",e);
        }
        // 重新定义返回值
        ResponseVo result = new ResponseVo();
        String url = request.getRequestURL().toString();
        if(StringUtils.hasText(request.getQueryString())){
            url += "?" + request.getQueryString();
        }
        result.setLink(url);
        result.setStatus("0");
        result.setError(utopiaMessage);
        return new ModelAndView("example").addObject("result",result);
    }
}

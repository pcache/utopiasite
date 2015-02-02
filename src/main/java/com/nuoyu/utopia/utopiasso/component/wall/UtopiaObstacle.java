package com.nuoyu.utopia.utopiasso.component.wall;

import com.nuoyu.utopia.utopiasso.common.context.UtopiaConstants;
import com.nuoyu.utopia.utopiasso.common.context.UtopiaTheadLocal;
import com.nuoyu.utopia.utopiasso.component.exception.UtopiaErrorDefinition;
import com.nuoyu.utopia.utopiasso.component.exception.UtopiaException;
import com.nuoyu.utopia.utopiasso.web.cmd.BaseCommand;
import com.nuoyu.utopia.utopiasso.web.dto.BaseDto;
import com.nuoyu.utopia.utopiasso.web.dto.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * utopia 拦截器
 * Created by liuxin3 on 2015/1/12.
 */
public class UtopiaObstacle implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(UtopiaObstacle.class);

    /**
     * 在业务处理器处理请求之前被调用
     * 如果返回false
     *    afterCompletion(),再退出拦截器链
     *
     * 如果返回true
     *    执行下一个拦截器,直到所有的拦截器都执行完毕
     *    再执行被拦截 从当前的拦截器往回执行所有拦截器的的Controller
     *    然后进入拦截器链,
     *    从最后一个拦截器往回执行所有的postHandle()
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 给每个request编码，查日志时可通过该值获取request完整记录
        MDC.put(UtopiaConstants.utopiaRequestUid, UtopiaConstants.APPLICATION_NAME +"-"+
                UUID.randomUUID().toString().replace("-", ""));
        // 进入request 记录日志
        log.info("request:::url:" + request.getRequestURL().toString() + "::::parameter::::" + request.getQueryString());
        // 将request等放入utopiaLocal
        UtopiaTheadLocal.setParamToLocal(UtopiaConstants.UTOPIA_CONTEXT_REQUEST,request);
        UtopiaTheadLocal.setParamToLocal(UtopiaConstants.UTOPIA_CONTEXT_RESPONSE,response);
        return true;
    }


    //在业务处理器处理请求执行完成后,生成视图之前执行的动作
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 取得model中所有要返回页面的Attribute
        Iterator<String> modelItem = modelAndView.getModel().keySet().iterator();
        // 待封装的响应
        List<BaseDto> responseList = new ArrayList<BaseDto>();
        // 需要从model中删除的Attribute的KEY值
        List<String> deleteList = new ArrayList<String>();
        // 重新封装对应的key值 顺序与responseList一致
        List<String> keyList = new ArrayList<String>();
        while(modelItem.hasNext()){
            String key = modelItem.next();
            Object value = modelAndView.getModel().get(key);
            // 如果Attribute中的值符合DTO定义，则加入待返回队列，并且将原来的加入删除队列
            if(value instanceof BaseDto){
                responseList.add((BaseDto) value);
                keyList.add(key);
                deleteList.add(key);
            }
            // 如果Attribute中的值符合COMMAND定义 加入删除队列
            if(value instanceof BaseCommand){
                deleteList.add(key);
            }
        }
        // 删除原有的Attribute
        for (String str:deleteList){
            modelAndView.getModel().remove(str);
        }
        // 重新定义返回值
        ResponseVo result = new ResponseVo();
        String url = request.getRequestURL().toString();
        if(StringUtils.hasText(request.getQueryString())){
            url += "?" + request.getQueryString();
        }
        result.setLink(url);
        result.setData(responseList);
        log.info("生成响应数据:::");
        modelAndView.addObject("result",result);
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用
     *
     *   当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

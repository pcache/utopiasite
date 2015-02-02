package com.nuoyu.utopia.utopiasso.component.wall;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 拦截器，记录controller及service轨迹，日志等行为
 * Created by liuxin3 on 2015/1/12.
 */
@Aspect
public class LogObstacle {

    private static final Logger log = LoggerFactory.getLogger(LogObstacle.class);

    @Before(value="execution(* com.nuoyu.utopia.utopiasso.web.controller.*Controller.*(..)) || execution(* com.nuoyu.utopia.utopiasso.interfaces.service..impl.*ServiceImpl.*(..)) || execution(* com.nuoyu.utopia.utopiasso.service..impl.*ServiceImpl.*(..))")
    public void beforeController(JoinPoint joinPoint){
        log.info("start:::"+joinPoint.getThis().toString());
    }

    @After(value="execution(* com.nuoyu.utopia.utopiasso.web.controller.*Controller.*(..))  || execution(* com.nuoyu.utopia.utopiasso.interfaces.service..impl.*ServiceImpl.*(..)) ||  execution(* com.nuoyu.utopia.utopiasso.service..impl.*ServiceImpl.*(..))")
    public void afterController(JoinPoint joinPoint) {
        log.info("end:::"+joinPoint.getThis().toString());
    }
}

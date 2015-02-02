package com.nuoyu.utopia.utopiasso.common.context;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

/**
 * Created by liuxin3 on 2015/1/9.
 * 继承ContextLoaderListener，集成logback，初始化logback路径
 */
public class UtopiaContextLoaderListener extends ContextLoaderListener {

    private static final Logger logger = LoggerFactory.getLogger(UtopiaContextLoaderListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        // 集成logback 用logback替代其他log
        configLogback(event);
        super.contextInitialized(event);
    }

    /**
     * 集成logback至utopia 配置文件log/logback.xml
     * @param event
     */
    private void configLogback(ServletContextEvent event) {
        //从web.xml中加载指定文件名的日志配置文件
        String logbackConfigLocation = event.getServletContext().getInitParameter(UtopiaConstants.LOG_CONFIG_LOCATION);
        // 获得日志文件绝对路径
        String fn = this.getClass().getClassLoader().getResource("/").getPath() + logbackConfigLocation;
        System.out.println("监听:"+fn);
        try{
            LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
            loggerContext.reset();
            JoranConfigurator joranConfigurator = new JoranConfigurator();
            joranConfigurator.setContext(loggerContext);
            // 设置配置
            joranConfigurator.doConfigure(fn);
            logger.info("loaded slf4j configure file from {}", fn);
        }catch(JoranException e){
            logger.error("can loading slf4j configure file from " + fn, e);
        }
    }

}

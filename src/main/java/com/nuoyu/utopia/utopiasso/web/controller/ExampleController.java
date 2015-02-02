package com.nuoyu.utopia.utopiasso.web.controller;

import com.nuoyu.utopia.utopiasso.component.exception.UtopiaException;
import com.nuoyu.utopia.utopiasso.pojo.Example;
import com.nuoyu.utopia.utopiasso.service.example.IExampleService;
import com.nuoyu.utopia.utopiasso.web.cmd.BaseCommand;
import com.nuoyu.utopia.utopiasso.web.dto.ExampleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;


/**
 * Created by liuxin3 on 2015/1/4.
 */
@Controller
@RequestMapping("/example")
public class ExampleController {

    @Autowired
    private IExampleService exampleService;

    private static WebApplicationContext webApplication = ContextLoader.getCurrentWebApplicationContext();

    @RequestMapping(value="/test", method= RequestMethod.GET)
    public String test(BaseCommand cmd,Model model) throws UtopiaException {//返回DTO？
        Example ep = new Example("yangjian",28,"nv");
        model.addAttribute("example",new ExampleDto(ep));
        /*
        if(true) {
            throw new UtopiaException(UtopiaErrorDefinition.BUSINESS_NO_USER);
            throw new NullPointerException();
        }
        */
        //RemoteExampleServiceImpl remoteExampleService = (RemoteExampleServiceImpl)webApplication.getBean("remoteExampleService");
        //remoteExampleService.test2();
        model.addAttribute("example2",new ExampleDto(ep));
        return "example";
    }



}

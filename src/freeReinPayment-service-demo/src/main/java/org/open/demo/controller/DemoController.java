package org.open.demo.controller;

import org.open.demo.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 任传君
 * @create 2018-07-31 16:24
 **/
@RestController
@RequestMapping("/demo")
public class DemoController {


    @Autowired
    private IDemoService demoService;

    @RequestMapping(value = "/demo1" ,method = RequestMethod.POST)
    public Object demo1(String id){
        this.demoService.getDemoService(id);
        return "";
    }

    @RequestMapping(value = "/demo2" ,method = RequestMethod.POST)
    public Object demo2(String id){
        this.demoService.getDemoService2(id);
        return "";
    }
}

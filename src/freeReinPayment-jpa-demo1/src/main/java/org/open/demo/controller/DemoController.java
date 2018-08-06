package org.open.demo.controller;

import org.open.demo.service.IDemoService;
import org.open.model.FQResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 任传君
 * @create 2018-08-01 15:54
 **/
@RestController
@RequestMapping("/demo1/demo")
public class DemoController {

    @Autowired
    private IDemoService demoService;

    @RequestMapping(value = "/savedemo" ,method = RequestMethod.POST)
    public FQResult<Object> saveDemo(String id){
        return this.demoService.saveDemo(id);
    }
}

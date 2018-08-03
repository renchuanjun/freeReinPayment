package org.open.demo.service.impl;

import org.open.demo.api.IDemo1Api;
import org.open.demo.service.IDemoService;
import org.open.model.FQResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author 任传君
 * @create 2018-07-31 16:29
 **/
@Service(value = "iDemoService")
public class DemoServiceImpl implements IDemoService {

    @Autowired
    private IDemo1Api demo1Api;

//    @Autowired
//    private IDemo2Api demo2Api;
//
//    @Autowired
//    private IDemo3Api demo3Api;

    @Override
    public FQResult<Object> getDemoService(String id) {
        demo1Api.getJpa1Demo(id);
        return null;
    }
}

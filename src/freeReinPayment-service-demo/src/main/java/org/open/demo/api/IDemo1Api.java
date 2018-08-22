package org.open.demo.api;


import org.open.annotation.MyTransactional;
import org.open.demo.hystrix.Demo1ApiHystrix;

import org.open.enums.TransactionalRoleEnum;
import org.open.enums.TransactionalTypeEnum;
import org.open.feigns.MyRestTemplateConfiguration;
import org.open.model.FQResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 任传君
 * @create 2018-07-31 16:31
 **/
@FeignClient(value = "jpa-demo1" ,fallback= Demo1ApiHystrix.class)
public interface IDemo1Api {
    
    @RequestMapping(value = "/demo1/demo/savedemo", method = RequestMethod.POST)
    @MyTransactional(destination = "demo",transactionalType = TransactionalTypeEnum.COERCIVE_IDENTUCAL ,role = TransactionalRoleEnum.START)
    FQResult<Object> getJpa1Demo(String id);

    @RequestMapping(value = "/demo1/demo/savedemo2", method = RequestMethod.POST)
    @MyTransactional(destination = "demo",transactionalType = TransactionalTypeEnum.COERCIVE_IDENTUCAL ,role = TransactionalRoleEnum.START)
    FQResult<Object> getJpa1Demo2(String id);
}

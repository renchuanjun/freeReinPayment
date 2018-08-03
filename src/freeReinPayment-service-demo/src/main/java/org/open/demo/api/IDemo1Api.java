package org.open.demo.api;


import org.open.demo.hystrix.Demo1ApiHystrix;

import org.open.model.FQParam2;
import org.open.model.FQResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 任传君
 * @create 2018-07-31 16:31
 **/
@FeignClient(value = "${jpa.demo1}" ,fallback= Demo1ApiHystrix.class)
public interface IDemo1Api {
    
    @RequestMapping(value = "/jpa1/demo", method = RequestMethod.POST)
//    @MyTransactional(destination = "demo",transactionalType = TransactionalTypeEnum.COERCIVE_IDENTUCAL ,role = TransactionalRoleEnum.START)
    FQResult<Object> getJpa1Demo(String id);


}

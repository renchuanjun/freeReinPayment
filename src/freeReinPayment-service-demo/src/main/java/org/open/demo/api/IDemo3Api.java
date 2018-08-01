package org.open.demo.api;

import org.open.annotation.MyTransactional;
import org.open.annotation.TransactionalRoleEnum;
import org.open.demo.hystrix.Demo3ApiHystrix;
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
@FeignClient(value = "${jpa.demo3}" ,fallback= Demo3ApiHystrix.class)
public interface IDemo3Api {
    
    @RequestMapping(value = "/jpa3/demo", method = RequestMethod.POST)
    @MyTransactional(destination = "demo" ,role = TransactionalRoleEnum.PROVIDER)
    FQResult<Object> getJpa3Demo(@RequestBody FQParam2<String, Object> hnaParam);


}

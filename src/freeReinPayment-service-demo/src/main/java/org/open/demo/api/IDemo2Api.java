package org.open.demo.api;

import org.open.demo.hystrix.Demo2ApiHystrix;
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
//@FeignClient(value = "${jpa.demo2}" ,fallback= Demo2ApiHystrix.class)
public interface IDemo2Api {
    
    @RequestMapping(value = "/jpa2/demo", method = RequestMethod.POST)
    FQResult<Object> getJpa2Demo(@RequestBody FQParam2<String, Object> hnaParam);


}

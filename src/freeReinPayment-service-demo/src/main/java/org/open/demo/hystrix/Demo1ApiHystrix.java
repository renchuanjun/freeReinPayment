package org.open.demo.hystrix;

import org.open.demo.api.IDemo1Api;
import org.open.model.FQParam2;
import org.open.model.FQResult;
import org.springframework.stereotype.Component;

/**
 * @author 任传君
 * @create 2018-07-31 16:32
 **/
@Component
public class Demo1ApiHystrix implements IDemo1Api {

    @Override
    public FQResult<Object> getJpa1Demo(String id) {
        FQResult<Object> fqResult = new FQResult<Object>();
        System.out.println("进入熔断");
        fqResult.setStateCode("-9999");
        fqResult.setDetailInfo("getTestList-fallback");
        return fqResult;
    }

    @Override
    public FQResult<Object> getJpa1Demo2(String id) {
        FQResult<Object> fqResult = new FQResult<Object>();
        System.out.println("进入熔断");
        fqResult.setStateCode("-9999");
        fqResult.setDetailInfo("getTestList-fallback");
        return fqResult;
    }
}

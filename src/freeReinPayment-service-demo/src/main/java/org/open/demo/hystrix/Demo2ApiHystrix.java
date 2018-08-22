
package org.open.demo.hystrix;

import org.open.demo.api.IDemo2Api;
import org.open.model.FQParam2;
import org.open.model.FQResult;
import org.springframework.stereotype.Component;


/**
 * @author 任传君
 * @create 2018-07-31 16:32
 **/

@Component
public class Demo2ApiHystrix implements IDemo2Api {
    @Override
    public FQResult<Object> getJpa2Demo(String id) {
        FQResult<Object> fqResult = new FQResult<Object>();
        fqResult.setStateCode("-9999");
        fqResult.setDetailInfo("getTestList-fallback");
        return fqResult;
    }
}


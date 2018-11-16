package org.open.system.api;

import org.open.model.FQParam2;
import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.system.hystrix.OperategroupApiHystrix;
import org.open.system.model.SysOperategroup;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@FeignClient(value = "jpa-common",fallback=OperategroupApiHystrix.class)
public interface IOperategroupApi {

    @RequestMapping(value = "/system/operategroup/list", method = RequestMethod.POST)
    FQResult<PaginationSupport<SysOperategroup>> selectSysOperategroupByPage(@RequestBody FQParam2<SysOperategroup,PagerAndOrderByArgs> fqParam2);
}

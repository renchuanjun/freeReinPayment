package org.open.system.service;

import org.open.model.FQParam2;
import org.open.model.FQResult;
import org.open.model.SubFQResult;
import org.open.system.hystric.PermissionServiceHystrix;
import org.open.system.model.SysPermission;
import org.open.system.model.SysPermissionOperategroup;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by lenovo on 2017/8/10.
 */
@FeignClient(value = "${gateway.common}",fallback= PermissionServiceHystrix.class)
public interface IPermissionService {

    @RequestMapping(value = "/system/permission/getpermissionbyall" ,method = RequestMethod.POST)
    FQResult<SubFQResult<List<SysPermission>,List<SysPermissionOperategroup>>> getPermissionByAll(@RequestBody FQParam2<String,Integer> hnaParam2);

}

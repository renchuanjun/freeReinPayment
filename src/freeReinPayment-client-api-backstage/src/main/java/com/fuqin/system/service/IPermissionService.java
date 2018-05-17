package com.fuqin.system.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fuqin.model.FQParam2;
import com.fuqin.model.FQResult;
import com.fuqin.model.SubFQResult;
import com.fuqin.system.hystric.PermissionServiceHystrix;
import com.fuqin.system.model.SysPermission;
import com.fuqin.system.model.SysPermissionOperategroup;

import java.util.List;

/**
 * Created by lenovo on 2017/8/10.
 */
@FeignClient(value = "${gateway.common}",fallback= PermissionServiceHystrix.class)
public interface IPermissionService {

    @RequestMapping(value = "/system/permission/getpermissionbyall" ,method = RequestMethod.POST)
    FQResult<SubFQResult<List<SysPermission>,List<SysPermissionOperategroup>>> getPermissionByAll(@RequestBody FQParam2<String,Integer> hnaParam2);

}

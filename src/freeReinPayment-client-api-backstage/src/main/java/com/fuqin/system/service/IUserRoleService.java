package com.fuqin.system.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fuqin.model.FQParam2;
import com.fuqin.model.FQResult;
import com.fuqin.system.hystric.UserRoleServiceHystrix;
import com.fuqin.system.model.SysUserRole;

@FeignClient(value = "${gateway.common}",fallback= UserRoleServiceHystrix.class)
public interface IUserRoleService {

	
	@RequestMapping(value = "/system/userrole/listjsontorole", method = RequestMethod.POST)
	FQResult<List<SysUserRole>> getSysUserRoleToRole(@RequestBody FQParam2<Integer, String> fqParam2);

}

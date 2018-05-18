package org.open.system.service;

import java.util.List;

import org.open.model.FQParam2;
import org.open.model.FQResult;
import org.open.system.hystric.UserRoleServiceHystrix;
import org.open.system.model.SysUserRole;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${gateway.common}",fallback= UserRoleServiceHystrix.class)
public interface IUserRoleService {

	
	@RequestMapping(value = "/system/userrole/listjsontorole", method = RequestMethod.POST)
	FQResult<List<SysUserRole>> getSysUserRoleToRole(@RequestBody FQParam2<Integer, String> fqParam2);

}

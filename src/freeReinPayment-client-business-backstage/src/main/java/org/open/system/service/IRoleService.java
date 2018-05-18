package org.open.system.service;

import java.util.List;

import org.open.model.FQParam2;
import org.open.model.FQParam3;
import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.system.hystric.RoleServiceHystrix;
import org.open.system.model.SysDatapermission;
import org.open.system.model.SysRole;
import org.open.system.model.SysUserRole;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${gateway.common}",fallback=RoleServiceHystrix.class)
public interface IRoleService {
	
	@RequestMapping(value = "/system/role/list" ,method = RequestMethod.POST)
	FQResult<PaginationSupport<SysRole>> getSysRoleList(@RequestBody FQParam2<SysRole, PagerAndOrderByArgs> hnaParam);

	@RequestMapping(value = "/system/role/submit" ,method = RequestMethod.POST)
	FQResult<Object> saveSysRole(@RequestBody FQParam3<SysRole, List<SysDatapermission>, List<SysUserRole>> param3);

	@RequestMapping(value = "/system/role/edit/{id}" ,method = RequestMethod.GET)
	FQResult<SysRole> getSysRole(@PathVariable("id") String id);

	@RequestMapping(value = "/system/role/delete" ,method = RequestMethod.POST)
	FQResult<Object> deleteSysRole(@RequestBody List<SysRole> list);

}

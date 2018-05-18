package com.fuqin.system.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fuqin.model.FQParam2;
import com.fuqin.model.FQParam3;
import com.fuqin.model.FQResult;
import com.fuqin.model.PagerAndOrderByArgs;
import com.fuqin.model.PaginationSupport;
import com.fuqin.system.hystric.RoleServiceHystrix;
import com.fuqin.system.model.SysDatapermission;
import com.fuqin.system.model.SysRole;
import com.fuqin.system.model.SysUserRole;

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

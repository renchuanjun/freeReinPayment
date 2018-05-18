package org.open.system.service;

import java.util.List;

import org.open.model.FQParam2;
import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.system.hystric.UserServiceHystrix;
import org.open.system.model.SysUser;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${gateway.common}",fallback= UserServiceHystrix.class)
public interface IUserService {

	@RequestMapping(value = "/system/user/list" ,method = RequestMethod.POST)
	FQResult<PaginationSupport<SysUser>> getSysUserList(@RequestBody FQParam2<SysUser, PagerAndOrderByArgs> hnaParam);

	@RequestMapping(value = "/system/user/savesysuser" ,method = RequestMethod.POST)
	FQResult<Object> saveSysUser(@RequestBody SysUser user);
	
	@RequestMapping(value = "/system/user/selectsysuser" ,method = RequestMethod.POST)
	FQResult<Object> selectSysUser(@RequestBody SysUser user);

	@RequestMapping(value = "/system/user/getsysuser/{userId}" ,method = RequestMethod.GET)
	FQResult<SysUser> getSysUser(@PathVariable("userId") String userId);

	@RequestMapping(value = "/system/user/delete" ,method = RequestMethod.POST)
	FQResult<Object> deleteSysUser(@RequestBody List<SysUser> list);

}

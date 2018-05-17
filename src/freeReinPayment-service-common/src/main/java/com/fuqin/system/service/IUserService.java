package com.fuqin.system.service;

import com.fuqin.model.FQResult;
import com.fuqin.model.PagerAndOrderByArgs;
import com.fuqin.model.PaginationSupport;
import com.fuqin.model.SubFQResult;
import com.fuqin.system.model.SysDatapermission;
import com.fuqin.system.model.SysPermission;
import com.fuqin.system.model.SysUser;

import java.util.List;

/**
 * Created by lenovo on 2017/8/1.
 */
public interface IUserService {

	FQResult<PaginationSupport<SysUser>> selectSysUserByPage(SysUser user, PagerAndOrderByArgs args);

	FQResult<Object> saveSysUser(SysUser user);

	FQResult<SysUser> getSysUser(String id);

	FQResult<Object> deleteSysUser(List<SysUser> list);

	FQResult<SubFQResult<SysUser,SubFQResult<List<SysDatapermission>,List<SysPermission>>>> login(SysUser user);

	FQResult<Object> selectSysUser(SysUser user);
}

package org.open.system.service;

import java.util.List;

import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.model.SubFQResult;
import org.open.system.model.SysDatapermission;
import org.open.system.model.SysPermission;
import org.open.system.model.SysUser;

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

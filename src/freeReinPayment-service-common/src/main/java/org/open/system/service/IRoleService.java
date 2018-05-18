package org.open.system.service;


import java.util.List;

import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.system.model.SysDatapermission;
import org.open.system.model.SysRole;
import org.open.system.model.SysUserRole;

/**
 * Created by lenovo on 2017/8/1.
 */
public interface IRoleService {

	FQResult<PaginationSupport<SysRole>> selectSysRoleByPage(SysRole role, PagerAndOrderByArgs args);

	FQResult<Object> saveSysRole(SysRole role,List<SysDatapermission> datapermissionList, List<SysUserRole> userRoleList);

	FQResult<SysRole> getSysRole(String id);

	FQResult<Object> deleteSysRole(List<SysRole> list);
}

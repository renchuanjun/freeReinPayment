package com.fuqin.system.service;


import com.fuqin.model.FQResult;
import com.fuqin.model.PagerAndOrderByArgs;
import com.fuqin.model.PaginationSupport;
import com.fuqin.system.model.SysDatapermission;
import com.fuqin.system.model.SysRole;
import com.fuqin.system.model.SysUserRole;

import java.util.List;

/**
 * Created by lenovo on 2017/8/1.
 */
public interface IRoleService {

	FQResult<PaginationSupport<SysRole>> selectSysRoleByPage(SysRole role, PagerAndOrderByArgs args);

	FQResult<Object> saveSysRole(SysRole role,List<SysDatapermission> datapermissionList, List<SysUserRole> userRoleList);

	FQResult<SysRole> getSysRole(String id);

	FQResult<Object> deleteSysRole(List<SysRole> list);
}

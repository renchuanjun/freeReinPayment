package org.open.system.service;


import java.util.List;

import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.system.model.SysOperategroup;

/**
 * Created by lenovo on 2017/8/1.
 */
public interface IOperategroupService {

	FQResult<PaginationSupport<SysOperategroup>> selectSysOperategroupByPage(SysOperategroup operategroup, PagerAndOrderByArgs args);

	FQResult<Object> saveSysOperategroup(SysOperategroup operategroup,String permissionIds,String hkPermissionIds);

	FQResult<SysOperategroup> getSysOperategroup(String id);

	FQResult<Object> deleteSysOperategroup(List<SysOperategroup> list);

	FQResult<List<SysOperategroup>> getSysOperategroupToRole(Integer foreBackType,String id);

}

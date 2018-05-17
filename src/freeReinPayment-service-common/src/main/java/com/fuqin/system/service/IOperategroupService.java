package com.fuqin.system.service;


import com.fuqin.model.FQResult;
import com.fuqin.model.PagerAndOrderByArgs;
import com.fuqin.model.PaginationSupport;
import com.fuqin.system.model.SysOperategroup;

import java.util.List;

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

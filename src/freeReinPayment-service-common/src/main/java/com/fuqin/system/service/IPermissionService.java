package com.fuqin.system.service;

import com.fuqin.model.FQResult;
import com.fuqin.model.SubFQResult;
import com.fuqin.system.model.SysPermission;
import com.fuqin.system.model.SysPermissionOperategroup;

import java.util.List;

/**
 * Created by lenovo on 2017/8/10.
 */
public interface IPermissionService {

	FQResult<SubFQResult<List<SysPermission>,List<SysPermissionOperategroup>>> getPermissionByAll(String groupId, Integer foreBackType);
}

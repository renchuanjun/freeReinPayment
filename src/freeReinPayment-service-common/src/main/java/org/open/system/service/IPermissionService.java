package org.open.system.service;

import java.util.List;

import org.open.model.FQResult;
import org.open.model.SubFQResult;
import org.open.system.model.SysPermission;
import org.open.system.model.SysPermissionOperategroup;

/**
 * Created by lenovo on 2017/8/10.
 */
public interface IPermissionService {

	FQResult<SubFQResult<List<SysPermission>,List<SysPermissionOperategroup>>> getPermissionByAll(String groupId, Integer foreBackType);
}

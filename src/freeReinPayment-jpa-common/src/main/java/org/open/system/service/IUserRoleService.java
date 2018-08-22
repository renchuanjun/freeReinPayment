package org.open.system.service;


import java.util.List;

import org.open.model.FQResult;
import org.open.system.model.SysUserRole;

/**
 * Created by lenovo on 2017/8/16.
 */
public interface IUserRoleService {

	FQResult<List<SysUserRole>> getSysUserRoleToRole(Integer foreBackType, String roleId);
}

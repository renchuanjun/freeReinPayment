package com.fuqin.system.service;


import com.fuqin.model.FQResult;
import com.fuqin.system.model.SysUserRole;

import java.util.List;

/**
 * Created by lenovo on 2017/8/16.
 */
public interface IUserRoleService {

	FQResult<List<SysUserRole>> getSysUserRoleToRole(Integer foreBackType, String roleId);
}

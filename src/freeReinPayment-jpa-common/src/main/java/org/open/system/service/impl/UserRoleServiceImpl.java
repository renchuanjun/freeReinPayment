
package org.open.system.service.impl;

import org.open.model.FQResult;
import org.open.system.dao.SysUserRoleMapper;
import org.open.system.model.SysUserRole;
import org.open.system.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2017/8/16.
 */
@Service(value = "iUserRoleService")
public class UserRoleServiceImpl implements IUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public FQResult<List<SysUserRole>> getSysUserRoleToRole(Integer foreBackType, String roleId) {
    	FQResult<List<SysUserRole>> fqResult = new FQResult<>();
        try {
            List<SysUserRole> list = this.sysUserRoleMapper.selectSysUserRoleToRole(foreBackType,roleId);
            fqResult.setResult(list);

            fqResult.setSuccess(true);
        } catch (Exception e) {
        	fqResult.setException(e);
        }
        return fqResult;
    }
}

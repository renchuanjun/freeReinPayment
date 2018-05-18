package org.open.system.controller;

import org.open.BaseController;
import org.open.model.FQParam2;
import org.open.model.FQResult;
import org.open.system.model.SysUserRole;
import org.open.system.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lenovo on 2017/8/16.
 */
@RestController
@RequestMapping("/system/userrole")
public class UserRoleController extends BaseController {

    @Autowired
    private IUserRoleService iUserRoleService;

    @RequestMapping(value = "/listjsontorole" ,method = RequestMethod.POST)
    public FQResult<List<SysUserRole>> getSysUserRoleToRole(@RequestBody FQParam2<Integer, String> fqParam2){
        Integer foreBackType = fqParam2.getT();
        String roleId = fqParam2.getK();
    	return iUserRoleService.getSysUserRoleToRole(foreBackType,roleId);
    }
}

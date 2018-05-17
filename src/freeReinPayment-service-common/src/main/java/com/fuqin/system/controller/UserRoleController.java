package com.fuqin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fuqin.BaseController;
import com.fuqin.model.FQParam2;
import com.fuqin.model.FQResult;
import com.fuqin.system.model.SysUserRole;
import com.fuqin.system.service.IUserRoleService;

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

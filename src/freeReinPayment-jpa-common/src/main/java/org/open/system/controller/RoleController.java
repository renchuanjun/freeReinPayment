package org.open.system.controller;

import org.open.BaseController;
import org.open.model.*;
import org.open.system.model.SysDatapermission;
import org.open.system.model.SysRole;
import org.open.system.model.SysUserRole;
import org.open.system.service.IRoleService;
import org.open.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lenovo on 2017/8/1.
 */
@RestController
@RequestMapping("/system/role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService iRoleService;

    @RequestMapping(value = "/list" ,method = RequestMethod.POST)
    public FQResult<PaginationSupport<SysRole>> getSysRoleList(@RequestBody FQParam2<SysRole,PagerAndOrderByArgs> hnaParam2){

        SysRole role = hnaParam2.getT();
        PagerAndOrderByArgs args = hnaParam2.getK();
        return iRoleService.selectSysRoleByPage(role,args);
    }


    @RequestMapping(value = "/submit" ,method = RequestMethod.POST)
    public FQResult<Object> saveSysRole(@RequestBody FQParam3<SysRole,List<SysDatapermission>,List<SysUserRole>> param3){
        SysRole role = param3.getT();
        List<SysDatapermission> datapermissionList = param3.getK();
        List<SysUserRole> userRoleList = param3.getV();
        return iRoleService.saveSysRole(role,datapermissionList,userRoleList);
    }


    @RequestMapping(value = "/edit/{id}" ,method = RequestMethod.GET)
    public FQResult<SysRole> getSysRole(@PathVariable("id") String id){
        return iRoleService.getSysRole(id);
    }

    @RequestMapping(value = "/delete" ,method = RequestMethod.POST)
    public FQResult<Object> deleteSysRole(@RequestBody List<SysRole> list){

        return iRoleService.deleteSysRole(list);
    }
}

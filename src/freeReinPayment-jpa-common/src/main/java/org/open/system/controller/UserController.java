package org.open.system.controller;


import org.open.BaseController;
import org.open.model.*;
import org.open.system.model.SysDatapermission;
import org.open.system.model.SysPermission;
import org.open.system.model.SysUser;
import org.open.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lenovo on 2017/8/1.
 */
@RestController
@RequestMapping("/system/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "/list" ,method = RequestMethod.POST)
    public FQResult<PaginationSupport<SysUser>> getSysUserList(@RequestBody FQParam2<SysUser,PagerAndOrderByArgs> hnaParam2){

        SysUser user = hnaParam2.getT();
        PagerAndOrderByArgs args = hnaParam2.getK();
        return iUserService.selectSysUserByPage(user,args);
    }


    @RequestMapping(value = "/savesysuser" ,method = RequestMethod.POST)
    public FQResult<Object> saveSysUser(@RequestBody SysUser user){
        return iUserService.saveSysUser(user);
    }

    							
    @RequestMapping(value = "/getsysuser/{userId}" ,method = RequestMethod.GET)
    public FQResult<SysUser> getSysUser(@PathVariable("userId") String userId){
        return iUserService.getSysUser(userId);
    }

    @RequestMapping(value = "/delete" ,method = RequestMethod.POST)
    public FQResult<Object> deleteSysUser(@RequestBody List<SysUser> list){

        return iUserService.deleteSysUser(list);
    }

    @RequestMapping(value = "/login" ,method = RequestMethod.POST)
    public FQResult<SubFQResult<SysUser,SubFQResult<List<SysDatapermission>,List<SysPermission>>>> login(@RequestBody SysUser user){
        return iUserService.login(user);
    }
    
    @RequestMapping(value = "/selectsysuser" ,method = RequestMethod.POST)
    public FQResult<Object> selectUser(@RequestBody SysUser user){
    	return this.iUserService.selectSysUser(user);
    }
}

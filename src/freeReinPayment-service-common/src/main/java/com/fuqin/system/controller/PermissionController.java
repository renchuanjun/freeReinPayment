package com.fuqin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fuqin.BaseController;
import com.fuqin.model.FQResult;
import com.fuqin.model.FQParam2;
import com.fuqin.model.SubFQResult;
import com.fuqin.system.model.SysPermission;
import com.fuqin.system.model.SysPermissionOperategroup;
import com.fuqin.system.service.IPermissionService;

import java.util.List;

/**
 * Created by lenovo on 2017/8/10.
 */
@RestController
@RequestMapping("/system/permission")
public class PermissionController extends BaseController {

    @Autowired
    private IPermissionService iPermissionService;

    @RequestMapping(value = "/getpermissionbyall" ,method = RequestMethod.POST)
    public FQResult<SubFQResult<List<SysPermission>,List<SysPermissionOperategroup>>> getOrganByAll(@RequestBody FQParam2<String,Integer> hnaParam2){
        String groupId = hnaParam2.getT();
        Integer foreBackType = hnaParam2.getK();
        //System.out.println("foreBackType"+foreBackType);
        return iPermissionService.getPermissionByAll(groupId,foreBackType);
    }
}

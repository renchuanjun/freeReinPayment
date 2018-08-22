package org.open.system.controller;

import org.open.BaseController;
import org.open.model.FQParam2;
import org.open.model.FQResult;
import org.open.model.SubFQResult;
import org.open.system.model.SysPermission;
import org.open.system.model.SysPermissionOperategroup;
import org.open.system.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

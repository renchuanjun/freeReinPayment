package org.open.system.controller;

import org.open.BaseController;
import org.open.model.FQParam2;
import org.open.model.FQParam3;
import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.system.model.SysOperategroup;
import org.open.system.model.SysUser;
import org.open.system.service.IOperategroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lenovo on 2017/8/1.
 */
@RestController
@RequestMapping("/system/operategroup")
public class OperategroupController extends BaseController {

    @Autowired
    private IOperategroupService iOperategroupService;

    @RequestMapping(value = "/list" ,method = RequestMethod.POST)
    public FQResult<PaginationSupport<SysOperategroup>> getSysOperategroupList(@RequestBody FQParam2<SysOperategroup,PagerAndOrderByArgs> hnaParam2){
        SysOperategroup operategroup = hnaParam2.getT();
        PagerAndOrderByArgs args = hnaParam2.getK();
        return iOperategroupService.selectSysOperategroupByPage(operategroup,args);
    }


    @RequestMapping(value = "/submit" ,method = RequestMethod.POST)
    public FQResult<Object> saveSysOperategroup(@RequestBody FQParam3<SysOperategroup,String,String> hnaParam3){
        SysOperategroup operategroup = hnaParam3.getT();
        String permissionIds = hnaParam3.getK();
        String hkPermissionIds = hnaParam3.getV();
        return iOperategroupService.saveSysOperategroup(operategroup,permissionIds,hkPermissionIds);
    }


    @RequestMapping(value = "/edit/{id}" ,method = RequestMethod.GET)
    public FQResult<SysOperategroup> getSysOperategroup(@PathVariable("id") String id){
        return iOperategroupService.getSysOperategroup(id);
    }

    @RequestMapping(value = "/delete" ,method = RequestMethod.POST)
    public FQResult<Object> deleteSysOperategroup(@RequestBody List<SysOperategroup> list){

        return iOperategroupService.deleteSysOperategroup(list);
    }

    @RequestMapping(value = "/listjsontorole" ,method = RequestMethod.POST)
    public FQResult<List<SysOperategroup>> getSysOperategroupToRole(@RequestBody FQParam2<Integer, String> fqParam2){
        Integer foreBackType = fqParam2.getT();
        String id = fqParam2.getK();
    	return iOperategroupService.getSysOperategroupToRole(foreBackType,id);
    }
}

package org.open.system.controller;

import org.open.BaseController;
import org.open.model.FQParam2;
import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.system.model.SysOrgan;
import org.open.system.service.IOrganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lenovo on 2017/8/1.
 */
@RestController
@RequestMapping("/system/organ")
public class OrganController extends BaseController {

    @Autowired
    private IOrganService iOrganService;

    /**
     * 获取组织机构树
     * @param foreBackType
     * @return
     */
    @RequestMapping(value = "/getorganbyall/{foreBackType}" ,method = RequestMethod.GET)
    public FQResult<List<SysOrgan>> getOrganByAll(@PathVariable("foreBackType") Integer foreBackType){

        return iOrganService.getOrganByAll(foreBackType);
    }

    @RequestMapping(value = "/list" ,method = RequestMethod.POST)
    public FQResult<PaginationSupport<SysOrgan>> getSysOrganList(@RequestBody FQParam2<SysOrgan,PagerAndOrderByArgs> hnaParam2){

        SysOrgan organ = hnaParam2.getT();
        PagerAndOrderByArgs args = hnaParam2.getK();
        return iOrganService.selectSysOrganByPage(organ,args);
    }


    @RequestMapping(value = "/submit" ,method = RequestMethod.POST)
    public FQResult<Object> saveSysOrgan(@RequestBody SysOrgan organ){
        return iOrganService.saveSysOrgan(organ);
    }


    @RequestMapping(value = "/edit/{id}" ,method = RequestMethod.GET)
    public FQResult<SysOrgan> getSysOrgan(@PathVariable("id") Integer id){
        return iOrganService.getSysOrgan(id);
    }

    @RequestMapping(value = "/delete" ,method = RequestMethod.POST)
    public FQResult<Object> deleteSysOrgan(@RequestBody List<SysOrgan> list){

        return iOrganService.deleteSysOrgan(list);
    }
}

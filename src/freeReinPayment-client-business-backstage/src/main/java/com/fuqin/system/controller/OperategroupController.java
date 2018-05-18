package com.fuqin.system.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fuqin.BaseController;
import com.fuqin.model.FQParam2;
import com.fuqin.model.FQParam3;
import com.fuqin.model.FQResult;
import com.fuqin.model.PagerAndOrderByArgs;
import com.fuqin.model.PaginationSupport;
import com.fuqin.system.model.SysOperategroup;
import com.fuqin.system.service.IOperategroupService;
import com.fuqin.system.viewmodel.OperategroupForm;
import com.fuqin.system.viewmodel.OperategroupParam;
import com.fuqin.utils.ClientUtils;
import com.fuqin.utils.JsonUtils;

@Controller
@RequestMapping("/system/operategroup")
public class OperategroupController extends BaseController {

    @Autowired
    private IOperategroupService operategroupService;

    @Autowired
    private ClientUtils clientUtils;
	
    private Integer foreBackType = 0;//代表后台
	
    
    /***
     * 加载列表页面
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "system/sysoperategroup/operategrouplist";
    }
    
    
    
	/**
     * 加载分页数据
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listjson", method = RequestMethod.POST)
    public String operategroupListJson(@ModelAttribute("param") OperategroupParam param){
        SysOperategroup operategroup = new SysOperategroup();
        operategroup.setGroupName(param.getGroupName());
        operategroup.setForeBackType(foreBackType.byteValue());
        PagerAndOrderByArgs args = clientUtils.getPagerAndOrderByArgs(super.request);
        FQParam2<SysOperategroup,PagerAndOrderByArgs> fqParam = new FQParam2<>();
        fqParam.setT(operategroup);
        fqParam.setK(args);
        FQResult<PaginationSupport<SysOperategroup>> hnaResult =  this.operategroupService.getSysOperategroupList(fqParam);
        String dataJson = super.SetTableDataJson(0, "[]");
        if (hnaResult.getSuccess()){
            PaginationSupport<SysOperategroup> paginationSupport = hnaResult.getResult();
            List<SysOperategroup> list = paginationSupport.getItems();
            int totalCount = paginationSupport.getTotalCount();
            String json = JsonUtils.SerializeJsonByList(list);
            dataJson = super.SetTableDataJson(totalCount,json);
        }
        return dataJson;
    }
    
    /**
     * 操作组列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listjsontorole", method = RequestMethod.POST)
    public String operategroupListJsonToRole(){
        String operateGroupData = request.getParameter("operateGroupData");
        if (!StringUtils.isEmpty(operateGroupData)){
            //System.out.println(operateGroupData);
            return super.SetTableDataJson(100, operateGroupData);
        }
        String dataJson = super.SetTableDataJson(0, "[]");
        String groupId = request.getParameter("groupId");
        FQParam2<Integer,String> fqParam = new FQParam2<>();
        fqParam.setK(groupId);
        fqParam.setT(foreBackType);
        if (!StringUtils.isEmpty(groupId)){
        	FQResult<List<SysOperategroup>> hnaResult =  this.operategroupService.getSysOperategroupToRole(fqParam);
            if (hnaResult.getSuccess()){
                List<SysOperategroup> list = hnaResult.getResult();
                String json = JsonUtils.SerializeJsonByList(list);
                dataJson = super.SetTableDataJson(list.size(),json);
            }
        }
        //System.out.println(dataJson);
        return dataJson;
    }
    
    
    /***
     * 添加
     * @param model
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String operategroupCreate(Model model) {
        OperategroupForm form = new OperategroupForm();
        model.addAttribute("form", form);
        return "system/sysoperategroup/operategroupedit";
    }

    /**
     * 编辑
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String operategroupEdit(Model model,@PathVariable String id) {
        OperategroupForm form=new OperategroupForm();
        FQResult<SysOperategroup> hnaResult = this.operategroupService.getSysOperategroup(id);
        if (hnaResult.getSuccess()){
            SysOperategroup operategroup = hnaResult.getResult();
            form = this.modelToForm(operategroup);
        }
        model.addAttribute("form", form);
        return "system/sysoperategroup/operategroupedit";
    }
    
    
    /**
     * 提交
     *
     * @return
     */
    @ResponseBody()
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String operategroupSubmit(@ModelAttribute("form") @Valid OperategroupForm form, BindingResult result) {

        if (result.hasErrors()) return super.formValidate(result);

        SysOperategroup operategroup = this.formToModel(form);
        FQParam3<SysOperategroup,String,String> fqParam3 = new FQParam3<>();
        fqParam3.setT(operategroup);
        //System.out.println(form.getPermissionIds());
        //System.out.println(form.getHkPermissionIds());
        fqParam3.setK(form.getPermissionIds());
        fqParam3.setV(form.getHkPermissionIds());
        FQResult<Object> hnaResult = this.operategroupService.saveSysOperategroup(fqParam3);

        if (hnaResult.getSuccess()){
            return super.outJsonStringSuccess();
        }
        else{
            return super.outJsonStringFail();
        }

    }

    /***
     * 删除(假删除)
     * @param ids
     * @return
     */
    @ResponseBody()
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String operategroupDelete(String ids) {

        List<SysOperategroup> list = new ArrayList<>();
        String[] items = ids.split("\\_");
        for (String item:items) {
            SysOperategroup operategroup = new SysOperategroup();
            operategroup.setGroupId(item);
            operategroup.setIsDeleted(Byte.valueOf("1"));

            String createBy = clientUtils.getCurrentUserId(request);
            String createByName = clientUtils.getCurrentName(request);
            Date curDate = Calendar.getInstance().getTime();

            operategroup.setUpdateBy(createBy);
            operategroup.setUpdateOn(curDate);
            operategroup.setUpdateByName(createByName);
            list.add(operategroup);
        }

        FQResult<Object> hnaResult = this.operategroupService.deleteSysOperategroup(list);

        if (hnaResult.getSuccess()){
            return super.outJsonStringSuccess();
        }
        else{
            if (StringUtils.isEmpty(hnaResult.getDetailInfo())){
                return super.outJsonStringFail();
            }
            else{
                return super.outJsonStringFail(hnaResult.getDetailInfo());
            }

        }

    }
    
    /***
     * model转viewmodel
     * @param operategroup
     * @return
     */
    private OperategroupForm modelToForm(SysOperategroup operategroup){
        OperategroupForm form = new OperategroupForm();
        form.setGroupId(operategroup.getGroupId());
        form.setGroupName(operategroup.getGroupName());
        form.setGroupDescription(operategroup.getGroupDescription());
        return form;
    }
    
    /***
     * viewmodel转model
     * @param form
     * @return
     */
    private SysOperategroup formToModel(OperategroupForm form){
        // 创建对象
        SysOperategroup operategroup = new SysOperategroup();
        operategroup.setGroupId(form.getGroupId());
        operategroup.setGroupName(form.getGroupName());
        operategroup.setGroupDescription(form.getGroupDescription());
        operategroup.setForeBackType(foreBackType.byteValue());

        /******必写代码********/
        String createBy = clientUtils.getCurrentUserId(request);
        String createByName = clientUtils.getCurrentName(request);
        Date curDate = Calendar.getInstance().getTime();
        //(主键为空则需要createon)
        if (StringUtils.isEmpty(operategroup.getGroupId())) {
            operategroup.setCreateBy(createBy);
            operategroup.setCreateOn(curDate);
            operategroup.setCreateByName(createByName);
        }
        operategroup.setUpdateBy(createBy);
        operategroup.setUpdateOn(curDate);
        operategroup.setUpdateByName(createByName);

        return operategroup;
    }

}

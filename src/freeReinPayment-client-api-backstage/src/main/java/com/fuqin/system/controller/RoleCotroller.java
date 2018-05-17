package com.fuqin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.fuqin.BaseController;
import com.fuqin.model.FQParam2;
import com.fuqin.model.FQParam3;
import com.fuqin.model.FQResult;
import com.fuqin.model.PagerAndOrderByArgs;
import com.fuqin.model.PaginationSupport;
import com.fuqin.system.model.SysDatapermission;
import com.fuqin.system.model.SysOperategroup;
import com.fuqin.system.model.SysRole;
import com.fuqin.system.model.SysUserRole;
import com.fuqin.system.service.IRoleService;
import com.fuqin.system.viewmodel.RoleForm;
import com.fuqin.system.viewmodel.RoleParam;
import com.fuqin.utils.ClientUtils;
import com.fuqin.utils.JsonUtils;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2017/8/11.
 */
@Controller
@RequestMapping("/system/role")
public class RoleCotroller extends BaseController {

    @Autowired
    private IRoleService iRoleService;

    @Autowired
    private ClientUtils clientUtils;

    private Integer foreBackType = 0;//代表后台
    /***
     * 加载列表页面
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {

        return "system/sysrole/rolelist";
    }

    /**
     * 加载分页数据
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listjson", method = RequestMethod.POST)
    public String roleListJson(@ModelAttribute("param") RoleParam param){
        SysRole role = new SysRole();
        role.setRoleName(param.getRoleName());
        role.setForeBackType(foreBackType.byteValue());
        PagerAndOrderByArgs args = clientUtils.getPagerAndOrderByArgs(super.request);
        FQParam2<SysRole,PagerAndOrderByArgs> hnaParam = new FQParam2<>();
        hnaParam.setT(role);
        hnaParam.setK(args);
        FQResult<PaginationSupport<SysRole>> hnaResult =  iRoleService.getSysRoleList(hnaParam);
        String dataJson = super.SetTableDataJson(0, "[]");
        if (hnaResult.getSuccess()){
            PaginationSupport<SysRole> paginationSupport = hnaResult.getResult();
            List<SysRole> list = paginationSupport.getItems();
            int totalCount = paginationSupport.getTotalCount();
            String json = JsonUtils.SerializeJsonByList(list);
            dataJson = super.SetTableDataJson(totalCount,json);
        }

        return dataJson;
    }

    /***
     * 添加
     * @param model
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String roleCreate(Model model) {
        RoleForm form = new RoleForm();
        model.addAttribute("form", form);
        return "system/sysrole/roleedit";
    }
    
    /**
     * 编辑
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String roleEdit(Model model,@PathVariable String id) {
        RoleForm form=new RoleForm();
        FQResult<SysRole> hnaResult = this.iRoleService.getSysRole(id);
        if (hnaResult.getSuccess()){
            SysRole role = hnaResult.getResult();
            form = this.modelToForm(role);
        }
        model.addAttribute("form", form);
        return "system/sysrole/roleedit";
    }

    /**
     * 提交
     *
     * @return
     */
    @ResponseBody()
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String roleSubmit(@ModelAttribute("form") @Valid RoleForm form, BindingResult result) throws Exception {
//        if (result.hasErrors()) return super.formValidate(result);
        SysRole role = this.formToModel(form);
        List<SysDatapermission> datapermissionList = null;
        List<SysUserRole> userRoleList = null;
        String groupId = null;
        //获取操作组
        if (!StringUtils.isEmpty(form.getOperateGroupData())){
            List<SysOperategroup> operategroupList = JsonUtils.DeserializeJsonByList(form.getOperateGroupData(), SysOperategroup.class);
            if (null != operategroupList && operategroupList.size() > 0){
                groupId = operategroupList.get(0).getGroupId();

            }
        }
        role.setGroupId(groupId);
        //获取数据权限
        if (!StringUtils.isEmpty(form.getDataPermissionData())){
            datapermissionList = JsonUtils.DeserializeJsonByList(form.getDataPermissionData(), SysDatapermission.class);
        }
        //获取人员
        if (!StringUtils.isEmpty(form.getUserData())){
            userRoleList = JsonUtils.DeserializeJsonByList(form.getUserData(), SysUserRole.class);
        }
        FQParam3<SysRole,List<SysDatapermission>,List<SysUserRole>> param3 = new FQParam3<>();
        param3.setT(role);
        param3.setK(datapermissionList);
        param3.setV(userRoleList);
        FQResult<Object> hnaResult = this.iRoleService.saveSysRole(param3);
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
    public String roleDelete(String ids) {

        List<SysRole> list = new ArrayList<>();
        String[] items = ids.split("\\_");
        for (String item:items) {
            SysRole role = new SysRole();
            role.setRoleId(item);
            role.setIsDeleted(Byte.valueOf("1"));

            String createBy = clientUtils.getCurrentUserId(request);
            String createByName = clientUtils.getCurrentName(request);
            Date curDate = Calendar.getInstance().getTime();

            role.setUpdateBy(createBy);
            role.setUpdateOn(curDate);
            role.setUpdateByName(createByName);
            list.add(role);
        }

        FQResult<Object> hnaResult = this.iRoleService.deleteSysRole(list);

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

    @RequestMapping(value = "/roleselectoperategroup", method = RequestMethod.GET)
    public String roleSelectOperateGroup(){
        return "system/sysrole/roleedit_select_operategroup";
    }

    @RequestMapping(value = "/roleselectdatapermission", method = RequestMethod.GET)
    public String roleSelectDataPermission(){
        return "system/sysrole/roleedit_select_datapermission";
    }

    @RequestMapping(value = "/roleselectuser", method = RequestMethod.GET)
    public String roleSelectUser(){
        return "system/sysrole/roleedit_select_user";
    }
    /***
     * viewmodel转model
     * @param form
     * @return
     */
    private SysRole formToModel(RoleForm form){
        // 创建对象
        SysRole role = new SysRole();
        role.setRoleId(form.getRoleId());
        role.setRoleName(form.getRoleName());
        role.setForeBackType(foreBackType.byteValue());
        role.setComments(form.getComments());

        /******必写代码********/
        String createBy = clientUtils.getCurrentUserId(request);
        String createByName = clientUtils.getCurrentName(request);
        Date curDate = Calendar.getInstance().getTime();
        //(主键为空则需要createon)
        if (StringUtils.isEmpty(role.getRoleId())) {
            role.setCreateBy(createBy);
            role.setCreateOn(curDate);
            role.setCreateByName(createByName);
        }
        role.setUpdateBy(createBy);
        role.setUpdateOn(curDate);
        role.setUpdateByName(createByName);

        return role;
    }

    /***
     * model转viewmodel
     * @param role
     * @return
     */
    private RoleForm modelToForm(SysRole role){
        RoleForm form = new RoleForm();
        form.setRoleId(role.getRoleId());
        form.setRoleName(role.getRoleName());
        form.setComments(role.getComments());
        form.setGroupId(role.getGroupId());
        return form;
    }
}

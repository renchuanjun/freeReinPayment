package com.fuqin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fuqin.BaseController;
import com.fuqin.model.FQParam2;
import com.fuqin.model.FQResult;
import com.fuqin.system.model.SysUserRole;
import com.fuqin.system.service.IUserRoleService;
import com.fuqin.utils.JsonUtils;

import java.util.List;

/**
 * Created by lenovo on 2017/8/16.
 */
@Controller
@RequestMapping("/system/userrole")
public class UserRoleController extends BaseController {

    @Autowired
    private IUserRoleService iUserRoleService;

    private Integer foreBackType = 0;//代表后台

    @ResponseBody
    @RequestMapping(value = "/listjsontorole", method = RequestMethod.POST)
    public String userListJsonToRole(){

        String userData = request.getParameter("userData");

        if (!StringUtils.isEmpty(userData)){
            //System.out.println(operateGroupData);
            return super.SetTableDataJson(100, userData);
        }
        String dataJson = super.SetTableDataJson(0, "[]");
        String roleId = request.getParameter("roleId");
        if (!StringUtils.isEmpty(roleId)){
        	FQParam2<Integer, String> fqParam2 = new FQParam2<Integer, String>();
        	fqParam2.setT(foreBackType);
        	fqParam2.setK(roleId);
        	FQResult<List<SysUserRole>> hnaResult =  this.iUserRoleService.getSysUserRoleToRole(fqParam2);
            if (hnaResult.getSuccess()){
                List<SysUserRole> list = hnaResult.getResult();
                String json = JsonUtils.SerializeJsonByList(list);
                dataJson = super.SetTableDataJson(list.size(),json);
            }
        }

        return dataJson;
    }
}

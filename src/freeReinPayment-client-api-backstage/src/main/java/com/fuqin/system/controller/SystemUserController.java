package com.fuqin.system.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fuqin.BaseController;
import com.fuqin.ConfigProperties;
import com.fuqin.model.FQResult;
import com.fuqin.model.FQParam2;
import com.fuqin.model.PagerAndOrderByArgs;
import com.fuqin.model.PaginationSupport;
import com.fuqin.system.model.SysUser;
import com.fuqin.system.service.IUserService;
import com.fuqin.system.viewmodel.UserForm;
import com.fuqin.system.viewmodel.UserParam;
import com.fuqin.utils.AesUtils;
import com.fuqin.utils.ClientUtils;
import com.fuqin.utils.DateTimeUtils;
import com.fuqin.utils.JsonUtils;
import com.fuqin.utils.MD5Utils;

@Controller
@RequestMapping("/system/user")
public class SystemUserController extends BaseController{

    @Autowired
    private ClientUtils clientUtils;
    
    @Autowired
    private IUserService iUserService;
	
    @Autowired
	private ConfigProperties configProperties;
    
    /***
     * 用户列表
     */
    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    public String userList()  {
    	System.out.println("1111");
        return "system/user/userlist";
    }
    
    /***
     * 添加用户
     */
    @RequestMapping(value = "/adduser/{organId}", method = RequestMethod.GET)
    public String addUser(Model model, @PathVariable String organId)  {
        UserForm form = new UserForm();
        form.setOrganId(organId);
        model.addAttribute("form", form);
        model.addAttribute("formName", "form1");
        model.addAttribute("actionName", "save");
        return "system/user/adduser";
    }
    
    /***
     * 修改用户
     * @throws Exception 
     */
    @RequestMapping(value = "/edituser/{userId}", method = RequestMethod.GET)
    public String editUser(Model model,@PathVariable String userId) throws Exception{
    	SysUser sysUser = new SysUser();
    	FQResult<SysUser> fqResult = this.iUserService.getSysUser(userId);
    	if(fqResult.getSuccess()){
    		sysUser = fqResult.getResult();
    		String phone = sysUser.getMobliePhone();
    		if(!StringUtils.isEmpty(phone))
    			sysUser.setMobliePhone(AesUtils.decrypt(configProperties.getAesKey(), phone));
    		model.addAttribute("form", sysUser);
    	}else{
    		model.addAttribute("form", sysUser);
    	}
    	
        return "system/user/edituser";
    }
    
    
    
    /**
     * 加载分页数据
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listjson", method = RequestMethod.POST)
    public String userListJson(@ModelAttribute("param") UserParam param){
        SysUser user = new SysUser();
        user.setForeBackType((byte) 0);
        String cName = param.getcName();
        user.setName(cName);
        String organId = param.getOrganId();
        user.setOrganId(organId);
        PagerAndOrderByArgs args = clientUtils.getPagerAndOrderByArgs(super.request);
        FQParam2<SysUser,PagerAndOrderByArgs> hnaParam = new FQParam2<>();
        hnaParam.setT(user);
        hnaParam.setK(args);
        FQResult<PaginationSupport<SysUser>> hnaResult =  this.iUserService.getSysUserList(hnaParam);
        String dataJson = super.SetTableDataJson(0, "[]");
        if (hnaResult.getSuccess()){
            PaginationSupport<SysUser> paginationSupport = hnaResult.getResult();
            List<SysUser> list = paginationSupport.getItems();
            int totalCount = paginationSupport.getTotalCount();
            String json = JsonUtils.SerializeJsonByList(list);
            dataJson = super.SetTableDataJson(totalCount,json);
        }
        return dataJson;
    }
    
    /**
     * 添加用户
     * @return
     */
    /**
     * @param userParam
     * @return
     * @throws ParseException 
     */
    @ResponseBody
    @RequestMapping(value = "/saveorupdateuser", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("param") UserParam userParam) throws Exception{
    	SysUser user = new SysUser();
    	String userId = userParam.getUserId();
    	user.setUserId(userId);
    	String userName = userParam.getUserName();
    	user.setUserName(userName);
    	String cName = userParam.getcName();
    	user.setName(cName);
    	String userpwd = userParam.getUserpwd();
    	user.setPassword(MD5Utils.GetMD5Code(userpwd));
    	Byte sex = userParam.getSex();
    	user.setSex(sex);
    	String mobliePhone = userParam.getMobliePhone();
    	user.setMobliePhone(AesUtils.encrypt(configProperties.getAesKey(), mobliePhone));
    	String email = userParam.getEmail();
    	user.setEmail(email);
    	Byte superUser = userParam.getSuperUser();
    	user.setSuperUser(superUser);
    	String organ_id = userParam.getOrganId();
    	user.setOrganId(organ_id);
    	//获取当登录人id
    	user.setCreateBy(clientUtils.getCurrentUserId(request));
    	user.setUpdateBy(clientUtils.getCurrentUserId(request));
    	user.setCreateOn(new Date());
    	user.setUpdateOn(new Date());
    	//密码过期时间
    	user.setPasswordLastChangeDate(DateTimeUtils.getEndSomeTime(6));
    	FQResult<Object> hnaResult =  this.iUserService.saveSysUser(user);
    	if(hnaResult.getSuccess()){
    		return super.outJsonStringSuccess();
    	}else{
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
    public String userDelete(String ids) {
        List<SysUser> list = new ArrayList<>();
        String[] items = ids.split("\\_");
        for (String item:items) {
            SysUser user = new SysUser();
            user.setUserId(item);
            user.setIsDeleted(Byte.valueOf("1"));
            String createBy = clientUtils.getCurrentUserId(request);
            String createByName = clientUtils.getCurrentName(request);
            Date curDate = Calendar.getInstance().getTime();
            user.setUpdateBy(createBy);
            user.setUpdateOn(curDate);
            user.setUpdateByName(createByName);
            list.add(user);
        }
        FQResult<Object> fqResult = this.iUserService.deleteSysUser(list);
        if (fqResult.getSuccess()){
            return super.outJsonStringSuccess();
        }else{
            if (StringUtils.isEmpty(fqResult.getDetailInfo())){
                return super.outJsonStringFail();
            }else{
                return super.outJsonStringFail(fqResult.getDetailInfo());
            }
        }
    }
    
    

    /**
     * 验证用户名是否重复
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectusername", method = RequestMethod.POST)
    public String selectUserName(String userName){
    	SysUser user = new SysUser();
    	user.setUserName(userName);
    	FQResult<Object> hnaResult =  this.iUserService.selectSysUser(user);
    	if(hnaResult.getSuccess()){
    		return super.outJsonStringSuccess();
    	}else{
    		return super.outJsonStringFail();
    	}
    	
    }

    
}

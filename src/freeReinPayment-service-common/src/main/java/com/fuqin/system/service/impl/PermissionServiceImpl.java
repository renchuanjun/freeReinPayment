package com.fuqin.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fuqin.model.FQResult;
import com.fuqin.model.SubFQResult;
import com.fuqin.system.dao.SysPermissionMapper;
import com.fuqin.system.dao.SysPermissionOperategroupMapper;
import com.fuqin.system.model.SysPermission;
import com.fuqin.system.model.SysPermissionOperategroup;
import com.fuqin.system.service.IPermissionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/8/10.
 */
@Service(value = "iPermissionService")
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysPermissionOperategroupMapper sysPermissionOperategroupMapper;

    @Override
    public FQResult<SubFQResult<List<SysPermission>,List<SysPermissionOperategroup>>> getPermissionByAll(String groupId, Integer foreBackType) {
        FQResult<SubFQResult<List<SysPermission>,List<SysPermissionOperategroup>>> fqResult = new FQResult<>();
        SubFQResult<List<SysPermission>,List<SysPermissionOperategroup>> subFQResult = new SubFQResult<>();
        fqResult.setResult(subFQResult);
        try {
            Map<List<SysPermission>,List<SysPermissionOperategroup>> map = new HashMap<>();
            List<SysPermission> list = sysPermissionMapper.getPermissionByAll(foreBackType);
            subFQResult.setResult(list);
            subFQResult.setSuccess(true);
            if (!StringUtils.isEmpty(groupId)){
                List<SysPermissionOperategroup> list1= this.sysPermissionOperategroupMapper.getSysPermissionOperategroupByGroupId(groupId,foreBackType);
                subFQResult.setUserData(list1);
            }
            fqResult.setSuccess(true);
        } catch (Exception e) {
        	fqResult.setException(e);
        }
        return fqResult;
    }
}

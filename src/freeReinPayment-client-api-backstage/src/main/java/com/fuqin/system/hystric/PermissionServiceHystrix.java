package com.fuqin.system.hystric;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fuqin.model.FQParam2;
import com.fuqin.model.FQResult;
import com.fuqin.model.SubFQResult;
import com.fuqin.system.model.SysPermission;
import com.fuqin.system.model.SysPermissionOperategroup;
import com.fuqin.system.service.IPermissionService;

@Component
public class PermissionServiceHystrix implements IPermissionService {

	@Override
	public FQResult<SubFQResult<List<SysPermission>, List<SysPermissionOperategroup>>> getPermissionByAll(
			FQParam2<String, Integer> hnaParam2) {
		// TODO Auto-generated method stub
		return null;
	}

}

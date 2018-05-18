package org.open.system.hystric;

import java.util.List;

import org.open.model.FQParam2;
import org.open.model.FQResult;
import org.open.model.SubFQResult;
import org.open.system.model.SysPermission;
import org.open.system.model.SysPermissionOperategroup;
import org.open.system.service.IPermissionService;
import org.springframework.stereotype.Component;

@Component
public class PermissionServiceHystrix implements IPermissionService {

	@Override
	public FQResult<SubFQResult<List<SysPermission>, List<SysPermissionOperategroup>>> getPermissionByAll(
			FQParam2<String, Integer> hnaParam2) {
		// TODO Auto-generated method stub
		return null;
	}

}

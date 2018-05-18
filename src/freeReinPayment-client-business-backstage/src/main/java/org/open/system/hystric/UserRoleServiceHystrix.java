package org.open.system.hystric;

import java.util.List;

import org.open.model.FQParam2;
import org.open.model.FQResult;
import org.open.system.model.SysUserRole;
import org.open.system.service.IUserRoleService;
import org.springframework.stereotype.Component;

@Component
public class UserRoleServiceHystrix implements IUserRoleService{

	@Override
	public FQResult<List<SysUserRole>> getSysUserRoleToRole(FQParam2<Integer, String> fqParam2) {
		FQResult<List<SysUserRole>> fqResult = new FQResult<List<SysUserRole>>();
		fqResult.setStateCode("-9999");
		fqResult.setDetailInfo("getTestList-fallback");
		return fqResult;
	}

}

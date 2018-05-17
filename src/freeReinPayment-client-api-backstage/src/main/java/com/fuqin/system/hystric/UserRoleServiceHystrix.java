package com.fuqin.system.hystric;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fuqin.model.FQParam2;
import com.fuqin.model.FQResult;
import com.fuqin.system.model.SysUserRole;
import com.fuqin.system.service.IUserRoleService;

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

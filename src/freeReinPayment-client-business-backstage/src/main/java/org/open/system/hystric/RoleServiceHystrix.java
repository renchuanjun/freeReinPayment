package org.open.system.hystric;

import java.util.List;

import org.open.model.FQParam2;
import org.open.model.FQParam3;
import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.system.model.SysDatapermission;
import org.open.system.model.SysRole;
import org.open.system.model.SysUserRole;
import org.open.system.service.IRoleService;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceHystrix implements IRoleService{

	@Override
	public FQResult<PaginationSupport<SysRole>> getSysRoleList(FQParam2<SysRole, PagerAndOrderByArgs> hnaParam) {
		FQResult<PaginationSupport<SysRole>> fqResult = new FQResult<PaginationSupport<SysRole>>();
		fqResult.setStateCode("-9999");
		fqResult.setDetailInfo("getTestList-fallback");
		return fqResult;
	}

	@Override
	public FQResult<Object> saveSysRole(FQParam3<SysRole, List<SysDatapermission>, List<SysUserRole>> param3) {
		FQResult<Object> fqResult = new FQResult<Object>();
		fqResult.setStateCode("-9999");
		fqResult.setDetailInfo("getTestList-fallback");
		return fqResult;
	}

	@Override
	public FQResult<SysRole> getSysRole(String id) {
		FQResult<SysRole> fqResult = new FQResult<SysRole>();
		fqResult.setStateCode("-9999");
		fqResult.setDetailInfo("getTestList-fallback");
		return fqResult;
	}

	@Override
	public FQResult<Object> deleteSysRole(List<SysRole> list) {
		FQResult<Object> fqResult = new FQResult<Object>();
		fqResult.setStateCode("-9999");
		fqResult.setDetailInfo("getTestList-fallback");
		return fqResult;
	}

}

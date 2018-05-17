package com.fuqin.system.hystric;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fuqin.model.FQParam2;
import com.fuqin.model.FQParam3;
import com.fuqin.model.FQResult;
import com.fuqin.model.PagerAndOrderByArgs;
import com.fuqin.model.PaginationSupport;
import com.fuqin.system.model.SysDatapermission;
import com.fuqin.system.model.SysRole;
import com.fuqin.system.model.SysUserRole;
import com.fuqin.system.service.IRoleService;

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

package org.open.system.hystric;

import java.util.List;

import org.open.model.FQParam2;
import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.system.model.SysUser;
import org.open.system.service.IUserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceHystrix implements IUserService {

	@Override
	public FQResult<PaginationSupport<SysUser>> getSysUserList(FQParam2<SysUser, PagerAndOrderByArgs> hnaParam) {
		FQResult<PaginationSupport<SysUser>> fqResult = new FQResult<PaginationSupport<SysUser>>();
		fqResult.setStateCode("-9999");
		fqResult.setDetailInfo("getTestList-fallback");
        return fqResult;
	}

	@Override
	public FQResult<Object> saveSysUser(SysUser user) {
		FQResult<Object> fqResult  = new FQResult<Object>();
		fqResult.setStateCode("-9999");
		fqResult.setDetailInfo("getTestList-fallback");
		return fqResult;
	}

	@Override
	public FQResult<Object> selectSysUser(SysUser user) {
		FQResult<Object> fqResult  = new FQResult<Object>();
		fqResult.setStateCode("-9999");
		fqResult.setDetailInfo("getTestList-fallback");
		return fqResult;
	}

	@Override
	public FQResult<SysUser> getSysUser(String userId) {
		FQResult<SysUser> fqResult  = new FQResult<SysUser>();
		fqResult.setStateCode("-9999");
		fqResult.setDetailInfo("getTestList-fallback");
		return fqResult;
	}

	@Override
	public FQResult<Object> deleteSysUser(List<SysUser> list) {
		FQResult<Object> fqResult  = new FQResult<Object>();
		fqResult.setStateCode("-9999");
		fqResult.setDetailInfo("getTestList-fallback");
		return fqResult;
	}

}

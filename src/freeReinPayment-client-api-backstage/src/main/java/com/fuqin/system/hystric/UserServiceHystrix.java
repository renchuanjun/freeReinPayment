package com.fuqin.system.hystric;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fuqin.model.FQResult;
import com.fuqin.model.FQParam2;
import com.fuqin.model.PagerAndOrderByArgs;
import com.fuqin.model.PaginationSupport;
import com.fuqin.system.model.SysUser;
import com.fuqin.system.service.IUserService;

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

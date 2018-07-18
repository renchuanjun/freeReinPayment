package org.open.system.hystric;

import java.util.List;

import org.open.model.FQParam2;
import org.open.model.FQParam3;
import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.system.model.SysOperategroup;
import org.open.system.service.IOperategroupService;
import org.springframework.stereotype.Component;

@Component
public class OperategroupServiceHystrix implements IOperategroupService{

	@Override
	public FQResult<PaginationSupport<SysOperategroup>> getSysOperategroupList(FQParam2<SysOperategroup, PagerAndOrderByArgs> hnaParam) {
		FQResult<PaginationSupport<SysOperategroup>> fqResult = new FQResult<PaginationSupport<SysOperategroup>>();
		fqResult.setStateCode("-9999");
		fqResult.setDetailInfo("getTestList-fallback");
		return fqResult;
	}

	@Override
	public FQResult<List<SysOperategroup>> getSysOperategroupToRole(FQParam2<Integer, String> fqParam) {
		FQResult<List<SysOperategroup>> fqResult = new FQResult<List<SysOperategroup>>();
		fqResult.setStateCode("-9999");
		fqResult.setDetailInfo("getTestList-fallback");
		return fqResult;
	}

	@Override
	public FQResult<SysOperategroup> getSysOperategroup(String id) {
		FQResult<SysOperategroup> fqResult = new FQResult<SysOperategroup>();
		fqResult.setStateCode("-9999");
		fqResult.setDetailInfo("getTestList-fallback");
		return fqResult;
	}

	@Override
	public FQResult<Object> saveSysOperategroup(FQParam3<SysOperategroup, String, String> fqParam3) {
		FQResult<Object> fqResult = new FQResult<Object>();
		fqResult.setStateCode("-9999");
		fqResult.setDetailInfo("getTestList-fallback");
		return fqResult;
	}

	@Override
	public FQResult<Object> deleteSysOperategroup(List<SysOperategroup> list) {
		FQResult<Object> fqResult = new FQResult<Object>();
		fqResult.setStateCode("-9999");
		fqResult.setDetailInfo("getTestList-fallback");
		return fqResult;
	}

}

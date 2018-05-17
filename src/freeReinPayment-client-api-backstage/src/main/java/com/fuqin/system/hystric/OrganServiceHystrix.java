package com.fuqin.system.hystric;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fuqin.model.FQParam2;
import com.fuqin.model.FQResult;
import com.fuqin.model.PagerAndOrderByArgs;
import com.fuqin.model.PaginationSupport;
import com.fuqin.system.model.SysOrgan;
import com.fuqin.system.service.IOrganService;

@Component
public class OrganServiceHystrix implements IOrganService {

	@Override
	public FQResult<PaginationSupport<SysOrgan>> getSysOrganList(FQParam2<SysOrgan, PagerAndOrderByArgs> hnaParam) {
		FQResult<PaginationSupport<SysOrgan>> fqResult = new FQResult<PaginationSupport<SysOrgan>>();
		fqResult.setStateCode("-9999");
		fqResult.setDetailInfo("getTestList-fallback");
		return fqResult;
	}

	@Override
	public FQResult<List<SysOrgan>> getOrganByAll(Integer foreBackType) {
		FQResult<List<SysOrgan>> fqResult = new FQResult<List<SysOrgan>>();
		fqResult.setStateCode("-9999");
		fqResult.setDetailInfo("getTestList-fallback");
		return fqResult;
	}

	@Override
	public FQResult<Object> saveSysOrgan(SysOrgan organ) {
		FQResult<Object> fqResult = new FQResult<Object>();
		fqResult.setStateCode("-9999");
		fqResult.setDetailInfo("getTestList-fallback");
		return fqResult;
	}

	@Override
	public FQResult<SysOrgan> getSysOrgan(Integer id) {
		FQResult<SysOrgan> fqResult = new FQResult<SysOrgan>();
		fqResult.setStateCode("-9999");
		fqResult.setDetailInfo("getTestList-fallback");
		return fqResult;
	}

	@Override
	public FQResult<Object> deleteSysOrgan(List<SysOrgan> list) {
		FQResult<Object> fqResult = new FQResult<Object>();
		fqResult.setStateCode("-9999");
		fqResult.setDetailInfo("getTestList-fallback");
		return fqResult;
	}

}

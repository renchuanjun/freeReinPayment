package org.open.system.hystric;

import java.util.List;

import org.open.model.FQParam2;
import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.system.model.SysOrgan;
import org.open.system.service.IOrganService;
import org.springframework.stereotype.Component;

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

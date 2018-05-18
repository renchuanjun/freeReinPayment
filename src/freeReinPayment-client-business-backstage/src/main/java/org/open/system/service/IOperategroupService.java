package org.open.system.service;

import java.util.List;

import org.open.model.FQParam2;
import org.open.model.FQParam3;
import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.system.hystric.OperategroupServiceHystrix;
import org.open.system.model.SysOperategroup;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${gateway.common}",fallback=OperategroupServiceHystrix.class)
public interface IOperategroupService {

	@RequestMapping(value = "/system/operategroup/list", method = RequestMethod.POST)
	FQResult<PaginationSupport<SysOperategroup>> getSysOperategroupList(@RequestBody FQParam2<SysOperategroup, PagerAndOrderByArgs> hnaParam);

	@RequestMapping(value = "/system/operategroup/listjsontorole", method = RequestMethod.POST)
	FQResult<List<SysOperategroup>> getSysOperategroupToRole(@RequestBody FQParam2<Integer, String> fqParam);

	@RequestMapping(value = "/system/operategroup/edit/{id}", method = RequestMethod.GET)
	FQResult<SysOperategroup> getSysOperategroup(@PathVariable("id") String id);
	
	@RequestMapping(value = "/system/operategroup/submit", method = RequestMethod.POST)
	FQResult<Object> saveSysOperategroup(@RequestBody FQParam3<SysOperategroup, String, String> fqParam3);

	@RequestMapping(value = "/system/operategroup/delete", method = RequestMethod.POST)
	FQResult<Object> deleteSysOperategroup(@RequestBody List<SysOperategroup> list);

	
}

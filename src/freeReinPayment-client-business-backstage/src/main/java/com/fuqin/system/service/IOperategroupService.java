package com.fuqin.system.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fuqin.model.FQParam2;
import com.fuqin.model.FQParam3;
import com.fuqin.model.FQResult;
import com.fuqin.model.PagerAndOrderByArgs;
import com.fuqin.model.PaginationSupport;
import com.fuqin.system.hystric.OperategroupServiceHystrix;
import com.fuqin.system.model.SysOperategroup;

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

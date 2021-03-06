package org.open.system.service;

import java.util.List;

import org.open.model.FQParam2;
import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.system.hystric.OrganServiceHystrix;
import org.open.system.model.SysOrgan;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${gateway.common}",fallback=OrganServiceHystrix.class)
public interface IOrganService {

	@RequestMapping(value = "/system/organ/list" ,method = RequestMethod.POST)
	FQResult<PaginationSupport<SysOrgan>> getSysOrganList(@RequestBody FQParam2<SysOrgan, PagerAndOrderByArgs> hnaParam);
	
	@RequestMapping(value = "/system/organ/getorganbyall/{foreBackType}", method = RequestMethod.GET)
	FQResult<List<SysOrgan>> getOrganByAll(@PathVariable("foreBackType") Integer foreBackType);
	
	@RequestMapping(value = "/system/organ/submit" ,method = RequestMethod.POST)
	FQResult<Object> saveSysOrgan(@RequestBody SysOrgan organ);

	@RequestMapping(value = "/system/organ/edit/{id}" ,method = RequestMethod.GET)
	FQResult<SysOrgan> getSysOrgan(@PathVariable("id")Integer id);

	@RequestMapping(value = "/system/organ/delete" ,method = RequestMethod.POST)
	FQResult<Object> deleteSysOrgan(@RequestBody List<SysOrgan> list);

}

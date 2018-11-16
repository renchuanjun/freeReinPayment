package org.open.system.api;

import org.open.system.hystrix.OrganApiHystrix;
import org.open.system.hystrix.PermissionApiHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "jpa-common",fallback=PermissionApiHystrix.class)
public interface IPermissionApi {
}

package org.open.system.api;

import org.open.system.hystrix.PermissionApiHystrix;
import org.open.system.hystrix.RoleApiHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "jpa-common",fallback=RoleApiHystrix.class)
public interface IRoleApi {
}

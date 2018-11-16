package org.open.system.api;

import org.open.system.hystrix.UserRoleApiHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "jpa-common",fallback=UserRoleApiHystrix.class)
public interface IUserRoleApi {
}

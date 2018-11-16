package org.open.system.api;

import org.open.system.hystrix.RoleApiHystrix;
import org.open.system.hystrix.UserApiHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "jpa-common",fallback=UserApiHystrix.class)
public interface IUserApi {
}

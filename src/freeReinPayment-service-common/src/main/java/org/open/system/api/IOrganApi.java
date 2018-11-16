package org.open.system.api;


import org.open.system.hystrix.OrganApiHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "jpa-common",fallback=OrganApiHystrix.class)
public interface IOrganApi {
}

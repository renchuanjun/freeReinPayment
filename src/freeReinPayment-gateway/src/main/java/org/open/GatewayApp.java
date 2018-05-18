package org.open;

import org.open.fileter.MyFilter;
import org.open.hystrix.ComonServiceHystrix;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class GatewayApp {
	public static void main(String[] args) {
		SpringApplication.run(GatewayApp.class, args);
	}
	
	@Bean
	public MyFilter myFilter(){
		return new MyFilter();
	}

    @Bean
    public ComonServiceHystrix zuulFallbackProvider2() {
        return new ComonServiceHystrix();
    }
}
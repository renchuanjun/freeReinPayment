package org.open;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;


/**
 * http://127.0.0.1:9301/hystrix 熔断器监控访问页面
 * client http://127.0.0.1:9101/hystrix.stream 监控路径
 * @author lenovo
 *
 */

@SpringBootApplication  
@EnableHystrix    //增加hystrix断路器  
@EnableHystrixDashboard  //支持hystrix dashboard  监控
public class HystrixApp {
	public static void main(String[] args) {
		SpringApplication.run(HystrixApp.class, args);
	}
}

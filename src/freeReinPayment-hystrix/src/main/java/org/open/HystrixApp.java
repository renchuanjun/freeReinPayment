package org.open;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;



/**
 * http://127.0.0.1:9301/hystrix 熔断器监控访问页面
 * client http://127.0.0.1:9101/hystrix.stream 单点监控路径
 * http://localhost:10002/turbine.stream集群监控
 * @author lenovo
 *
 */
@EnableEurekaClient
@SpringBootApplication  
@EnableHystrixDashboard
@EnableTurbine
public class HystrixApp{
    public static void main( String[] args ){
    	 SpringApplication.run(HystrixApp.class, args);
    }
}

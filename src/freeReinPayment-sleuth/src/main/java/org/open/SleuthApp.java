package org.open;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import zipkin.server.EnableZipkinServer;

@EnableZuulProxy
@EnableEurekaClient
@EnableZipkinServer
@SpringBootApplication
public class SleuthApp {
	public static void main(String[] args) {
		SpringApplication.run(SleuthApp.class, args);
	}
}
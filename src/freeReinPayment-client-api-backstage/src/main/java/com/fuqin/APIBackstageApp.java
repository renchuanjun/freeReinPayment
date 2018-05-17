package com.fuqin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import com.fuqin.interceptor.RemoteRequestInterceptor;

import feign.RequestInterceptor;

@SpringBootApplication
@EnableDiscoveryClient //注解来添加发现服务能力
@EnableFeignClients //注解开启Feign功能
@EnableAutoConfiguration
@EnableConfigurationProperties({ConfigProperties.class})
@EnableRedisHttpSession(redisNamespace="fuqinfinance-client-api-backstage")
public class APIBackstageApp {
	
    @Autowired
    private ConfigProperties configProperties;
	
    @Autowired
    private ResourceLoader resourceLoader;
    
	public static void main(String[] args) {
		SpringApplication.run(APIBackstageApp.class, args);
	}
	

    /***
     * 远程调用的拦截器
     * @return
     */
    @Bean
    public RequestInterceptor requestInterceptor(){
        RemoteRequestInterceptor remoteRequestInterceptor = new RemoteRequestInterceptor();
        remoteRequestInterceptor.setBaseConfigProperties(configProperties);
        remoteRequestInterceptor.setResourceLoader(resourceLoader);
        return remoteRequestInterceptor;
    }


    /***
     * spring session的注册
     * @return
     */
    @Bean
    @Order(2)
    public HttpSessionStrategy httpSessionStrategy() {
        return new CookieHttpSessionStrategy();
        //return new HeaderHttpSessionStrategy();
    }



    
    /***
     * 微应用的统一捕获异常
     * @return
     */
    @Bean
    public ExceptionHandler exceptionHandler(){
        return new ExceptionHandler();
    }
}
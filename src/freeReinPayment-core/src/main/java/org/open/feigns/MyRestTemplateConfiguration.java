package org.open.feigns;


import feign.Feign;
import feign.InvocationHandlerFactory;
import feign.auth.BasicAuthRequestInterceptor;
import feign.hystrix.HystrixFeign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author 任传君
 * @create 2018-08-02 17:00
 **/
@Configuration
public class MyRestTemplateConfiguration {

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        Feign.Builder builders = Feign.builder();
        MyRestTemplateInterceptor myRestTemplateInterceptor = new MyRestTemplateInterceptor();
        builders = builders.requestInterceptor(myRestTemplateInterceptor);
        InvocationHandlerFactory invocationHandlerFactory = invocationHandlerFactory();
        builders.invocationHandlerFactory(invocationHandlerFactory);
        HystrixFeign.Builder builder =  HystrixFeign.builder();
        return builders;
    }

    @Bean
    public InvocationHandlerFactory invocationHandlerFactory() {
        return (target, dispatch) -> {
            MyFeignHandler handler = new MyFeignHandler();
            handler.setTarget(target);
            handler.setHandlers(dispatch);
            return handler;
        };
    }



}

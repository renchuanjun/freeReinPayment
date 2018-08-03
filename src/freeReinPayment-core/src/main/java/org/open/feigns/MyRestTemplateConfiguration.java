package org.open.feigns;


import feign.Feign;
import feign.InvocationHandlerFactory;
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
        Feign.Builder builder = Feign.builder();
        MyRestTemplateInterceptor myRestTemplateInterceptor = new MyRestTemplateInterceptor();
        builder = builder.requestInterceptor(myRestTemplateInterceptor);
        InvocationHandlerFactory invocationHandlerFactory = invocationHandlerFactory();
        builder.invocationHandlerFactory(invocationHandlerFactory);
        return builder;
    }

    @Bean
    public InvocationHandlerFactory invocationHandlerFactory() {
        System.out.println("11111");
        return (target, dispatch) -> {
            MyFeignHandler handler = new MyFeignHandler();
            handler.setTarget(target);
            handler.setHandlers(dispatch);
            return handler;
        };
    }
}

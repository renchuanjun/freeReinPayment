package com.fuqin.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fuqin.ConfigProperties;

/**
 * Created by lenovo on 2017/4/13.
 */
@EnableConfigurationProperties({ConfigProperties.class})
@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Autowired
    private ConfigProperties configProperties;

    /***
     * 添加自定义拦截器
     * @param registry
     
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        RSAHandlerInterceptor rsaHandlerInterceptor = new RSAHandlerInterceptor();
        rsaHandlerInterceptor.setConfigProperties(configProperties);
        registry.addInterceptor(rsaHandlerInterceptor);
        super.addInterceptors(registry);
    }
    */
}

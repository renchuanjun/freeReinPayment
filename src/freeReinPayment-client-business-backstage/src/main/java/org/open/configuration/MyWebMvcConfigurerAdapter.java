package org.open.configuration;

import org.open.ConfigProperties;
import org.open.interceptor.AuthorityInterceptor;
import org.open.interceptor.CSRFInterceptor;
import org.open.interceptor.SecurityInterceptor;
import org.open.utils.ClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by lenovo on 2017/5/2.
 */
@Configuration
@EnableConfigurationProperties(ConfigProperties.class)
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private ClientUtils clientUtils;
    /***
     * 添加自定义拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	
        //安全控制
        SecurityInterceptor securityInterceptor = new SecurityInterceptor();
        securityInterceptor.setBaseConfigProperties(configProperties);
        InterceptorRegistration interceptor= registry.addInterceptor(securityInterceptor);
        String securityAllowUrl = configProperties.getSecurityAllowUrl();
        if (!StringUtils.isEmpty(securityAllowUrl)){
            String[] items = securityAllowUrl.split("\\|");
            interceptor.addPathPatterns(items);
        }
        String securityExcludeUrl = configProperties.getSecurityExcludeUrl();
        if (!StringUtils.isEmpty(securityExcludeUrl)){
            String[] items = securityExcludeUrl.split("\\|");
            interceptor.excludePathPatterns(items);
        }
        //权限控制
        AuthorityInterceptor authorityInterceptor = new AuthorityInterceptor();
        authorityInterceptor.setConfigProperties(configProperties);
        authorityInterceptor.setClientUtils(clientUtils);
        interceptor = registry.addInterceptor(authorityInterceptor);
        String authorityAllowUrl= configProperties.getAuthorityAllowUrl();
        if (!StringUtils.isEmpty(authorityAllowUrl)){
            String[] items = authorityAllowUrl.split("\\|");
            interceptor.addPathPatterns(items);
        }
        String authorityExcludeUrl = configProperties.getAuthorityExcludeUrl();
        if (!StringUtils.isEmpty(authorityExcludeUrl)){
            String[] items = authorityExcludeUrl.split("\\|");
            interceptor.excludePathPatterns(items);
        }

        //CSRF的安全控制
        CSRFInterceptor csrfInterceptor = new CSRFInterceptor();
        csrfInterceptor.setBaseConfigProperties(configProperties);
        interceptor = registry.addInterceptor(csrfInterceptor);
        String csrfAllowUrl = configProperties.getCsrfAllowUrl();
        if (!StringUtils.isEmpty(csrfAllowUrl)){
            String[] items = csrfAllowUrl.split("\\|");
            interceptor.addPathPatterns(items);
        }
        String csrfExcludeUrl = configProperties.getCsrfExcludeUrl();
        if (!StringUtils.isEmpty(csrfExcludeUrl)){
            String[] items = csrfExcludeUrl.split("\\|");
            interceptor.excludePathPatterns(items);
        }
        super.addInterceptors(registry);
    }


    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        registry.addViewController( "/" ).setViewName( "forward:/home.frl" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
    }



}

package org.open;



import org.open.listener.MyApplicationEnvironmentPreparedEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.io.ResourceLoader;

@EnableDiscoveryClient //该注解能激活Eureka中的DiscoveryClient实现，才能实现Controller中对服务信息的输出
@SpringBootApplication //是一个spring boot项目
@EnableAutoConfiguration
@EnableConfigurationProperties({ConfigProperties.class})
public class Demo1ServiceApp {

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private ResourceLoader resourceLoader;

    public static void main(String[] args) throws Exception {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Demo1ServiceApp.class);
        builder.listeners(new MyApplicationEnvironmentPreparedEventListener());
        builder.web(true).run(args);
    }


    /*@Bean
    public FilterRegistrationBean rsaFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("rsaFilter");
        RSAFilter rsaFilter = new RSAFilter();
        rsaFilter.setConfigProperties(configProperties);
        rsaFilter.setResourceLoader(resourceLoader);
        registrationBean.setFilter(rsaFilter);
        registrationBean.setOrder(1);
        List<String> urlList = new ArrayList<String>();
        urlList.add("/*");
        registrationBean.setUrlPatterns(urlList);
        return registrationBean;
    }*/
}

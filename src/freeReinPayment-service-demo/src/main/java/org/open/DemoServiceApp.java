package org.open;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;



@SpringBootApplication //是一个spring boot项目
@EnableDiscoveryClient //注解来添加发现服务能力
@EnableFeignClients //注解开启Feign功能
@EnableAutoConfiguration
@EnableConfigurationProperties({ConfigProperties.class})
public class DemoServiceApp {

    @Autowired
    private ConfigProperties configProperties;

//    @Autowired
//    private ResourceLoader resourceLoader;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DemoServiceApp.class, args);
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

package org.open;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer //注解启动一个服务注册中心提供给其他应用进行对话
@SpringBootApplication //是一个spring boot项目
public class RegisterCenterApp {

    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(RegisterCenterApp.class).web(true).run(args);
    }
}

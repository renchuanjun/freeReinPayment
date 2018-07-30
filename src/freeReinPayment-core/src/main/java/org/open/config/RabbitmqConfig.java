package org.open.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 任传君
 * @create 2018-07-27 17:31
 **/
@Configuration
public class RabbitmqConfig {

    /**
     * 服务器地址
     */
    @Value("${spring.rabbit.host}")
    private String host;

    /**
     * 服务器端口
     */
    @Value("${spring.rabbit.port}")
    private String port;

    /**
     * 用户名
     */
    @Value("${spring.rabbit.userName}")
    private String userName;
    /**
     * 密码
     */
    @Value("${spring.rabbit.passWord}")
    private String passWord;

    /**
     *指定连接到broker的Virtual host
     */
    @Value("${spring.rabbit.virtual-host}")
    private String virtualHost;

    /**
     *开启发布确认机制
     */
    private boolean publisherConfirms = true;

    /**
     * 设置消费者必须手动ack进行消息确认
     */
    private String acknowledgeMode = "MANUAL";

    /**
     * 是否在启动时就启动mq，默认: true)
     */
    private boolean autoStartup = true;


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(this.host,Integer.valueOf(this.port));
        connectionFactory.setUsername(this.userName);
        connectionFactory.setPassword(this.passWord);
        connectionFactory.setVirtualHost(this.virtualHost);
        connectionFactory.setPublisherConfirms(this.publisherConfirms);
        return connectionFactory;
    }


    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());

        return template;
    }

}

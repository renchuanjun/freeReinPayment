package org.open.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

import java.util.Properties;

/**
 * 在该事件中可以获取到SpringApplication对象，可做一些执行前的设置
 * Created by lenovo on 2017/5/11.
 */
public class MyApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {

    }
}

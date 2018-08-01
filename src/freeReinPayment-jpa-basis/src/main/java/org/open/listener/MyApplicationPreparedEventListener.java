package org.open.listener;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * ApplicationPreparedEvent:spring boot上下文context创建完成，但此时spring中的bean是没有完全加载完成的
 * 在获取完上下文后，可以将上下文传递出去做一些额外的操作。
 * 在该监听器中是无法获取自定义bean并进行操作的。
 * Created by lenovo on 2017/5/11.
 */
public class MyApplicationPreparedEventListener implements ApplicationListener<ApplicationPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent applicationPreparedEvent) {

    }
}

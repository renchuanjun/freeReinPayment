package org.open.task.demo;

import org.apache.log4j.Logger;
import org.open.ConfigProperties;
import org.open.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

/**
 * @author 任传君
 * @create 2018-07-17 16:01
 **/
@Component
public class Demo1Task extends TaskService {

    private Logger logger = Logger.getLogger(Demo1Task.class);

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Override
    public void startTask(String time) {
        if (this.configProperties.isTask()) {//是否启动定时器
            future = threadPoolTaskScheduler.schedule(() -> {
                System.out.println("定时任务1启动,定时规则 :" + time);
            }, new CronTrigger(time));
            System.out.println("定时任务1"+future.toString());
        }
    }
}

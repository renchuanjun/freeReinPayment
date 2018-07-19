package org.open.task.demo;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.open.ConfigProperties;
import org.open.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 任传君
 * @create 2018-07-18 15:32
 **/
@Component
public class Demo2Task extends TaskService {

    private Logger logger = Logger.getLogger(Demo2Task.class);

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Override
    public void startTask(String time) {
        if (this.configProperties.isTask()) {//是否启动定时器
            System.out.println("定时任务2"+(future == null));
            future = threadPoolTaskScheduler.schedule(() -> {
                System.out.println("定时任务2启动,定时规则 :" + time);
                System.out.println("定时任务2"+threadPoolTaskScheduler.toString());
                Date date = new Date();
                System.out.println("定时任务2执行时间"+DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
            }, new CronTrigger(time));

        }
    }
}

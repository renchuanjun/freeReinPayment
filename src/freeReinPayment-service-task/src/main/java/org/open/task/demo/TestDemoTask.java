package org.open.task.demo;

import org.apache.log4j.Logger;
import org.open.task.TaskService;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 任传君
 * @create 2018-07-17 16:01
 **/
@Component
public class TestDemoTask extends TaskService {

    private Logger logger = Logger.getLogger(TestDemoTask.class);

    @Override
    public void startTask(String time) {
        future = getThreadPoolTaskScheduler().schedule(() -> {
            System.out.println("定时任务1启动,定时规则 :"+time);
        }, new CronTrigger(time));
    }
}

package org.open.configuration;

import org.open.ConfigProperties;
import org.open.task.ITaskService;
import org.open.task.model.TimedTask;
import org.open.timedtask.dao.TimedTaskMapper;
import org.open.utils.TaskEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
@Order(1)
public class MyTaskApplicationRunner implements ApplicationRunner {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private TimedTaskMapper timedTaskMapper;


    /**
     * q启动项目时查询数据库启动定时器
     * @param applicationArguments
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments applicationArguments){
        if (this.configProperties.isTask()){//是否启动定时器
            List<TimedTask> list = this.timedTaskMapper.getTimedTaskList();
            for (TimedTask task: list) {
                String taskName = task.getTaskName();
                String startTime = task.getExecuteTime();
                Class<ITaskService> aclass = TaskEnum.Task.getClass(taskName);
                if(null != aclass){
                    ITaskService taskService = this.context.getBean(aclass);
                    taskService.startTask(startTime);
                }
            }
        }
    }
}

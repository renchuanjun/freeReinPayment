package org.open.task;

import org.open.task.model.TimedTask;
import org.open.timedtask.dao.TimedTaskMapper;
import org.open.utils.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledFuture;

/**
 * @author 任传君
 * @create 2018-07-12 10:38
 **/
@Component
public abstract class TaskService implements ITaskService {

    protected ScheduledFuture<?> future;

    @Autowired
    private TimedTaskMapper timedTaskMapper;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    public ThreadPoolTaskScheduler getThreadPoolTaskScheduler() {
        return threadPoolTaskScheduler;
    }

    abstract public void startTask(String time);

    @Override
    public void stopTask() {
        if (this.future != null) {
            this.future.cancel(true);
        }
    }

    @Override
    public void restartTask(String taskName) throws MyException {
        TimedTask timedTask = this.timedTaskMapper.selectByPrimaryTaskName(taskName);
        if(null != timedTask) {
            String time = timedTask.getExecuteTime();//查询数据库获取启动时间
            stopTask();
            startTask(time);
        }else {
            throw new MyException("无此定时任务");
        }
    }

    /**
     * 重置启动时间
     * @param time
     * @return
     */
    @Override
    public void resetTask(String time) {
        stopTask();
        startTask(time);
    }

}

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

    abstract public void startTask(String time);

    @Override
    public void stopTask() {
        if (this.future != null) {
            this.future.cancel(true);
            System.gc();
        }
    }

    @Override
    public void restartTask(TimedTask timedTask) throws MyException {
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
        System.gc();
        stopTask();
        startTask(time);
    }

}

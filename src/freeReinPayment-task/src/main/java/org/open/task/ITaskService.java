package org.open.task;


import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.task.model.TimedTask;
import org.open.utils.MyException;

/**
 * @author 任传君
 * @create 2018-07-11 11:59
 **/
public interface ITaskService {

    /**
     * 启动定时器
     * @param time
     * @return
     */
    void startTask(String time);

    /**
     * 停止定时器
     */
    void stopTask();

    /**
     * 重启时器
     */
    void restartTask(TimedTask timedTask) throws MyException, MyException;

    /**
     * 重置定时器
     */
    void resetTask(String time);

}

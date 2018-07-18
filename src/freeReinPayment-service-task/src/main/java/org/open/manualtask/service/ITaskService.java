package org.open.manualtask.service;

import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.task.model.TimedTask;

public interface ITaskService {

    /**
     * 列表查询
     * @param timedTask
     * @param args
     * @return
     */
    FQResult<PaginationSupport<TimedTask>> selectTaskByPage(TimedTask timedTask, PagerAndOrderByArgs args);
    /**
     * 停止定时任务
     * @return
     */
    FQResult<Object> stopTask(TimedTask timedTask);

    /**
     * 重启前定时任务
     * @param timedTask 定时任务名称
     * @return
     */
    FQResult<Object> restartTask(TimedTask timedTask);

    /**
     * 重置定时任务
     * @param timedTask
     * @return
     */
    FQResult<Object> resetTask(TimedTask timedTask);


}

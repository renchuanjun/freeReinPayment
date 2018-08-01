package org.open.manualtask.controller;

import org.open.BaseController;
import org.open.manualtask.service.ITaskService;
import org.open.model.FQParam2;
import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;

import org.open.task.model.TimedTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 任传君
 * @create 2018-07-17 17:39
 **/
@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {

    @Autowired
    private ITaskService taskService;

    /**
     * 列表查询
     * @param hnaParam2
     * @return
     */
    @RequestMapping(value = "/list" ,method = RequestMethod.POST)
    public FQResult<PaginationSupport<TimedTask>> getTaskList(@RequestBody FQParam2<TimedTask,PagerAndOrderByArgs> hnaParam2){
        TimedTask timedTask = hnaParam2.getT();
        PagerAndOrderByArgs args = hnaParam2.getK();
        return taskService.selectTaskByPage(timedTask,args);
    }

    /**
     * 结束定时任务
     * @param timedTask
     * @return
     */
    @RequestMapping(value = "/stoptask" ,method = RequestMethod.POST)
    public FQResult<Object> stopTask(@RequestBody TimedTask timedTask){
        return taskService.stopTask(timedTask);
    }

    /**
     * 重启定时任务
     * @param timedTask
     * @return
     */
    @RequestMapping(value = "/restarttask" ,method = RequestMethod.POST)
    public FQResult<Object> restartTask(@RequestBody TimedTask timedTask){
        return taskService.restartTask(timedTask);
    }

    /**
     * 重置定时任务
     * @param timedTask
     * @return
     */
    @RequestMapping(value = "/resettask" ,method = RequestMethod.POST)
    public FQResult<Object> resetTask(@RequestBody TimedTask timedTask){
        return taskService.resetTask(timedTask);
    }
}

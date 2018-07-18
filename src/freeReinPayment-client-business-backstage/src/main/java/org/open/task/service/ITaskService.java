package org.open.task.service;

import org.open.model.FQParam2;
import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.task.hystric.TaskServiceHystrix;
import org.open.task.model.TimedTask;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "${gateway.task}",fallback=TaskServiceHystrix.class)
public interface ITaskService {
    /***
     * 列表查询
     * @param fqParam
     * @return
     */
    @RequestMapping(value = "/task/list", method = RequestMethod.POST)
    FQResult<PaginationSupport<TimedTask>> getTaskList(FQParam2<TimedTask,PagerAndOrderByArgs> fqParam);

    /**
     * 停止或重启定时任务
     * @param
     * @return
     */
    @RequestMapping(value = "/task/stoptask", method = RequestMethod.POST)
    FQResult<Object> stopTask(TimedTask timedTask);


    /**
     * 停止或重启定时任务
     * @param
     * @return
     */
    @RequestMapping(value = "/task/restarttask", method = RequestMethod.POST)
    FQResult<Object> restartTask(TimedTask timedTask);
}

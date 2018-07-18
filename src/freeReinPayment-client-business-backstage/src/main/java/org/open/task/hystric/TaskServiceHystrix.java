package org.open.task.hystric;

import org.open.model.FQParam2;
import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.system.model.SysOperategroup;
import org.open.task.model.TimedTask;
import org.open.task.service.ITaskService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 任传君
 * @create 2018-07-18 10:22
 **/

@Component
public class TaskServiceHystrix implements ITaskService {

    @Override
    public FQResult<PaginationSupport<TimedTask>> getTaskList(FQParam2<TimedTask, PagerAndOrderByArgs> fqParam) {
        FQResult<PaginationSupport<TimedTask>> fqResult = new FQResult<PaginationSupport<TimedTask>>();
        fqResult.setStateCode("-9999");
        fqResult.setDetailInfo("getTestList-fallback");
        return fqResult;
    }

    @Override
    public FQResult<Object> stopTask(TimedTask timedTask) {
        FQResult<Object> fqResult = new FQResult<>();
        fqResult.setStateCode("-9999");
        fqResult.setDetailInfo("getTestList-fallback");
        return fqResult;
    }

    @Override
    public FQResult<Object> restartTask(TimedTask timedTask) {
        FQResult<Object> fqResult = new FQResult<>();
        fqResult.setStateCode("-9999");
        fqResult.setDetailInfo("getTestList-fallback");
        return fqResult;
    }
}

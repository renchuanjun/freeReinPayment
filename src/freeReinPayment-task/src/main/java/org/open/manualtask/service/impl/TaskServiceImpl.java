package org.open.manualtask.service.impl;

import org.open.manualtask.service.ITaskService;
import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.task.model.TimedTask;
import org.open.timedtask.dao.TimedTaskMapper;
import org.open.utils.MyException;
import org.open.utils.TaskEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 任传君
 * @create 2018-07-17 17:25
 **/
@Service(value = "iDemo1TaskService")
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private TimedTaskMapper timedTaskMapper;

    @Override
    public FQResult<PaginationSupport<TimedTask>> selectTaskByPage(TimedTask timedTask, PagerAndOrderByArgs args) {
        FQResult<PaginationSupport<TimedTask>> fqResult = new FQResult<>();

        try {
            List<TimedTask> list = timedTaskMapper.selectTimedTaskByPage(timedTask,args);
            int totalCount = timedTaskMapper.selectTimedTaskByPageCount(timedTask);
            PaginationSupport<TimedTask> paginationSupport = new PaginationSupport<TimedTask>(list,totalCount,args.getPageSize(),args.getCurrentPage());
            fqResult.setResult(paginationSupport);
            fqResult.setSuccess(true);
        } catch (Exception e) {
            fqResult.setException(e);
        }
        return fqResult;
    }

    @Override
    public FQResult<Object> stopTask(TimedTask timedTask) {
        FQResult<Object> fqResult = new FQResult<>();
        try {
            Integer id = timedTask.getTaskId();
            this.timedTaskMapper.updateByPrimaryKeySelective(timedTask);
            timedTask = this.timedTaskMapper.selectByPrimaryKey(id);
            String taskName = timedTask.getTaskName();
            Class<org.open.task.ITaskService> aclass = TaskEnum.Task.getClass(taskName);
            if(null != aclass) {
                org.open.task.ITaskService taskService = context.getBean(aclass);
                taskService.stopTask();
                fqResult.setSuccess(true);
            }else{
                throw new MyException("无此定时任务");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fqResult;
    }

    @Override
    public FQResult<Object> restartTask(TimedTask timedTask) {
        FQResult<Object> fqResult = new FQResult<>();
        try {
            Integer id = timedTask.getTaskId();
            this.timedTaskMapper.updateByPrimaryKeySelective(timedTask);
            timedTask = this.timedTaskMapper.selectByPrimaryKey(id);
            String taskName = timedTask.getTaskName();
            Class<org.open.task.ITaskService> aclass = TaskEnum.Task.getClass(taskName);
            if(null != aclass){
                org.open.task.ITaskService taskService = context.getBean(aclass);
                taskService.restartTask(timedTask);
                fqResult.setSuccess(true);
            }else{
                throw new MyException("无此定时任务");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fqResult;
    }

    @Override
    public FQResult<Object> resetTask(TimedTask timedTask) {
        FQResult<Object> fqResult = new FQResult<>();
        try {
            Integer id = timedTask.getTaskId();
            this.timedTaskMapper.updateByPrimaryKeySelective(timedTask);
            timedTask = this.timedTaskMapper.selectByPrimaryKey(id);
            String taskName = timedTask.getTaskName();
            String time = timedTask.getExecuteTime();
            Class<org.open.task.ITaskService> aclass = TaskEnum.Task.getClass(taskName);
            if(null != aclass) {
                org.open.task.ITaskService taskService = context.getBean(aclass);
                taskService.resetTask(time);
                fqResult.setSuccess(true);
            }else{
                throw new MyException("无此定时任务");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fqResult;
    }
}

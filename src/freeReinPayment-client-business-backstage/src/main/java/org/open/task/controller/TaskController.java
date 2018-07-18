package org.open.task.controller;

import org.open.BaseController;
import org.open.model.FQParam2;
import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.system.model.SysOperategroup;
import org.open.system.viewmodel.OperategroupParam;
import org.open.task.model.TimedTask;
import org.open.task.service.ITaskService;
import org.open.task.viewmodel.TaskParam;
import org.open.utils.ClientUtils;
import org.open.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 任传君
 * @create 2018-07-18 10:04
 **/
@Controller
@RequestMapping("/task")
public class TaskController extends BaseController {

    @Autowired
    private ClientUtils clientUtils;

    @Autowired
    private ITaskService taskService;

    /***
     * 加载列表页面
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "task/demo/tasklist";
    }


    /**
     * 加载分页数据
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listjson", method = RequestMethod.POST)
    public String taskListJson(@ModelAttribute("param") TaskParam param){
        TimedTask timedTask = new TimedTask();
        String taskName = param.getTaskName();
        timedTask.setTaskName(taskName);
        FQParam2<TimedTask,PagerAndOrderByArgs> fqParam = new FQParam2<>();
        PagerAndOrderByArgs args = clientUtils.getPagerAndOrderByArgs(super.request);
        args.setSortColumn("taskName");
        fqParam.setT(timedTask);
        fqParam.setK(args);
        FQResult<PaginationSupport<TimedTask>> hnaResult =  this.taskService.getTaskList(fqParam);
        String dataJson = super.SetTableDataJson(0, "[]");
        if (hnaResult.getSuccess()){
            PaginationSupport<TimedTask> paginationSupport = hnaResult.getResult();
            List<TimedTask> list = paginationSupport.getItems();
            int totalCount = paginationSupport.getTotalCount();
            String json = JsonUtils.SerializeJsonByList(list);
            dataJson = super.SetTableDataJson(totalCount,json);
        }
        return dataJson;
    }


    /**
     * 停止定时任务
     * @param id
     * @return
     */
    @ResponseBody()
    @RequestMapping(value = "/stoptask", method = RequestMethod.POST)
    public String stopTask(String id){
        TimedTask timedTask = new TimedTask();
        timedTask.setTaskId(Integer.valueOf(id));
        timedTask.setIsStart(0);
        FQResult<Object> fqResult = this.taskService.stopTask(timedTask);
        if (fqResult.getSuccess()){
            return super.outJsonStringSuccess();
        }
        return super.outJsonStringFail();
    }

    /**
     * 重启定时任务
     * @param id
     * @return
     */
    @ResponseBody()
    @RequestMapping(value = "/restarttask", method = RequestMethod.POST)
    public String restartTask(String id){
        TimedTask timedTask = new TimedTask();
        timedTask.setTaskId(Integer.valueOf(id));
        timedTask.setIsStart(1);
        FQResult<Object> fqResult = this.taskService.restartTask(timedTask);
        if (fqResult.getSuccess()){
            return super.outJsonStringSuccess();
        }
        return super.outJsonStringFail();
    }

}

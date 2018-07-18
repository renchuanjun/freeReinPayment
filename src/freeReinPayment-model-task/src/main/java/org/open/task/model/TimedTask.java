package org.open.task.model;

public class TimedTask implements java.io.Serializable{
    /**
     * 定时任务ID
     */
    private Integer taskId;

    /**
     * 定时任务名称
     */
    private String taskName;

    /**
     * 执行时间
     */
    private String executeTime;

    /**
     * 是否启动1启动0停止
     */
    private Integer isStart;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 定时器名称
     */
    private String name;

    /**
     * 定时任务ID
     */
    public Integer getTaskId() {
        return taskId;
    }

    /**
     * 定时任务ID
     */
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    /**
     * 定时任务名称
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * 定时任务名称
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * 执行时间
     */
    public String getExecuteTime() {
        return executeTime;
    }

    /**
     * 执行时间
     */
    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    /**
     * 是否启动1启动0停止
     */
    public Integer getIsStart() {
        return isStart;
    }

    /**
     * 是否启动1启动0停止
     */
    public void setIsStart(Integer isStart) {
        this.isStart = isStart;
    }

    /**
     * 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 定时器名称
     */
    public String getName() {
        return name;
    }

    /**
     * 定时器名称
     */
    public void setName(String name) {
        this.name = name;
    }
}
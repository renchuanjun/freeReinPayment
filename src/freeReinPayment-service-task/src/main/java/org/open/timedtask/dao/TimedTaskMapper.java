package org.open.timedtask.dao;

import org.apache.ibatis.annotations.Mapper;
import org.open.task.model.TimedTask;

import java.util.List;

@Mapper
public interface TimedTaskMapper {
    int deleteByPrimaryKey(Integer taskId);

    int insert(TimedTask record);

    int insertSelective(TimedTask record);

    TimedTask selectByPrimaryKey(Integer taskId);

    int updateByPrimaryKeySelective(TimedTask record);

    int updateByPrimaryKey(TimedTask record);

    List<TimedTask> getTimedTaskList();

    TimedTask selectByPrimaryTaskName(String taskName);

    int updateByPrimaryTaskName(TimedTask record);
}
package org.open.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.open.model.PagerAndOrderByArgs;
import org.open.system.model.SysOperategroup;

import java.util.List;

@Mapper
public interface SysOperategroupMapper {
    int deleteByPrimaryKey(String groupId);

    int insert(SysOperategroup record);

    int insertSelective(SysOperategroup record);

    SysOperategroup selectByPrimaryKey(String groupId);

    int updateByPrimaryKeySelective(SysOperategroup record);

    int updateByPrimaryKey(SysOperategroup record);


    List<SysOperategroup> selectSysOperategroupByPage(
            @Param("operategroup") SysOperategroup operategroup,
            @Param("args") PagerAndOrderByArgs args);

    int selectSysOperategroupByPageCount(
            @Param("operategroup") SysOperategroup operategroup);

    List<SysOperategroup> selectSysOperategroupToRole(@Param("foreBackType") Integer foreBackType,@Param("groupId") String groupId);
}
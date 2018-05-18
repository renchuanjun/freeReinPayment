package org.open.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.open.model.PagerAndOrderByArgs;
import org.open.system.model.SysOrgan;

import java.util.List;

@Mapper
public interface SysOrganMapper {
    int deleteByPrimaryKey(Integer organId);

    int insert(SysOrgan record);

    int insertSelective(SysOrgan record);

    SysOrgan selectByPrimaryKey(Integer organId);

    int updateByPrimaryKeySelective(SysOrgan record);

    int updateByPrimaryKey(SysOrgan record);

    List<SysOrgan> getOrganByAll(Integer foreBackType);

    List<SysOrgan> selectSysOrganByPage(
            @Param("organ") SysOrgan organ,
            @Param("args") PagerAndOrderByArgs args);

    int selectSysOrganByPageCount(
            @Param("organ") SysOrgan organ);
}
package com.fuqin.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fuqin.system.model.SysPermission;
import com.fuqin.system.model.SysPermissionKey;

import java.util.List;

@Mapper
public interface SysPermissionMapper {
    int deleteByPrimaryKey(String rowId);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(String rowId);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);


    List<SysPermission> getPermissionByAll(Integer foreBackType);

    /**
     * 通过操作组获取操作权限
     * @param items
     * @param foreBackType
     * @return
     */
    List<SysPermission> selectSysPermissionByOperateGroup(@Param("items")String[] items, @Param("foreBackType")Byte foreBackType);
}
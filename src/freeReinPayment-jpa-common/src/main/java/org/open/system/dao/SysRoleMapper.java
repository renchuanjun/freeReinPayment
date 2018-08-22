package org.open.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.open.model.PagerAndOrderByArgs;
import org.open.system.model.SysRole;
import org.open.system.model.SysUser;

import java.util.List;

@Mapper
public interface SysRoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> selectSysRoleByPage(
            @Param("role") SysRole role,
            @Param("args") PagerAndOrderByArgs args);

    int selectSysRoleByPageCount(
            @Param("role") SysRole role);

    /***
     * 通过角色获取操作组
     * @param items
     * @param foreBackType
     * @return
     */
    List<SysRole> selectGroupIdByRole(@Param("items")String[] items,@Param("foreBackType")Byte foreBackType);
}
package org.open.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.open.system.model.SysUserRole;

import java.util.List;

@Mapper
public interface SysUserRoleMapper {
    int deleteByPrimaryKey(String roleUserId);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(String roleUserId);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);


    List<SysUserRole> selectSysUserRoleToRole(@Param("foreBackType") Integer foreBackType, @Param("roleId") String roleId);

    int deleteSysUserRoleToRole(SysUserRole record);
}
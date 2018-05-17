package com.fuqin.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fuqin.model.PagerAndOrderByArgs;
import com.fuqin.system.model.SysUser;

import java.util.List;

@Mapper
public interface SysUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);


    List<SysUser> selectSysUserByPage(
            @Param("user") SysUser user,
            @Param("args") PagerAndOrderByArgs args);

    int selectSysUserByPageCount(
            @Param("user") SysUser user);

    List<SysUser> selectSysUser(SysUser record);

    int checkUserNameOnly(String userName);

    List<SysUser> selectRoleBySysUser(@Param("foreBackType")Byte foreBackType,@Param("userId")String userId);
}
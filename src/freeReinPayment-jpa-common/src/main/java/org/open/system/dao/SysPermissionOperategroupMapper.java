package org.open.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.open.system.model.SysPermissionOperategroup;

import java.util.List;

@Mapper
public interface SysPermissionOperategroupMapper {
    int deleteByPrimaryKey(String groupRowId);

    int insert(SysPermissionOperategroup record);

    int insertSelective(SysPermissionOperategroup record);

    SysPermissionOperategroup selectByPrimaryKey(String groupRowId);

    int updateByPrimaryKeySelective(SysPermissionOperategroup record);

    int updateByPrimaryKey(SysPermissionOperategroup record);

    /**
     * 删除权限组与权限的关联(假删除)
     * @param record
     * @return
     */
    int deleteSysPermissionOperategroup(SysPermissionOperategroup record);


    List<SysPermissionOperategroup> getSysPermissionOperategroupByGroupId(@Param("groupId") String groupId,@Param("foreBackType") Integer foreBackType);
}
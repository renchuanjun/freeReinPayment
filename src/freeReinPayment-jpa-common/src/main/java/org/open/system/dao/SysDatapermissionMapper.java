package org.open.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.open.system.model.SysDatapermission;

import java.util.List;

@Mapper
public interface SysDatapermissionMapper {
    int deleteByPrimaryKey(String dpId);

    int insert(SysDatapermission record);

    int insertSelective(SysDatapermission record);

    SysDatapermission selectByPrimaryKey(String dpId);

    int updateByPrimaryKeySelective(SysDatapermission record);

    int updateByPrimaryKey(SysDatapermission record);

    /**
     * 删除角色与数据权限的关联(假删除)
     * @param record
     * @return
     */
    int deleteSysDatapermissionToRole(SysDatapermission record);

    /***
     * 通过角色获取数据权限
     * @param items
     * @param foreBackType
     * @return
     */
    List<SysDatapermission> getDataIdByRole(@Param("items")String[] items,@Param("foreBackType")Byte foreBackType);
}
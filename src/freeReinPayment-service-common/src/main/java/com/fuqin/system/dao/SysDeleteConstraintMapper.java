package com.fuqin.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fuqin.system.model.SysDeleteConstraint;

import java.util.List;

@Mapper
public interface SysDeleteConstraintMapper {
    int deleteByPrimaryKey(Integer delId);

    int insert(SysDeleteConstraint record);

    int insertSelective(SysDeleteConstraint record);

    SysDeleteConstraint selectByPrimaryKey(Integer delId);

    int updateByPrimaryKeySelective(SysDeleteConstraint record);

    int updateByPrimaryKey(SysDeleteConstraint record);


    /***
     * 获取需要删除的约束
     * @param tableFrom
     * @return
     */
    List<SysDeleteConstraint> getDeletedConstraintList(String tableFrom);

    /***
     * 检测删除字段是否被使用
     * @param tableTo
     * @param tableToPkId
     * @param field
     * @param itemId
     * @return
     */

    Object checkDeletedConstraint(@Param("tableTo") String tableTo,
                                  @Param("tableToPkId") String tableToPkId,
                                  @Param("field") String field, @Param("itemId") String itemId);
}
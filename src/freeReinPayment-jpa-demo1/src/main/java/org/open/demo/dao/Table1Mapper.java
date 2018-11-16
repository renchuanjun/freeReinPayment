package org.open.demo.dao;


import org.apache.ibatis.annotations.Mapper;
import org.open.demo.model.Table1;

@Mapper
public interface Table1Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Table1 record);

    int insertSelective(Table1 record);

    Table1 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Table1 record);

    int updateByPrimaryKey(Table1 record);
}
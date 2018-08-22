package org.open.system.service.impl;

import org.apache.commons.lang.StringUtils;
import org.open.system.dao.SysDeleteConstraintMapper;
import org.open.system.model.SysDeleteConstraint;
import org.open.system.service.IDeleteConstraintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2017/8/8.
 */
@Service(value = "iDeleteConstraintService")
public class DeleteConstraintServiceImpl implements IDeleteConstraintService{

    @Autowired
    private SysDeleteConstraintMapper sysDeleteConstraintMapper;
    /***
     * 检测删除约束是否存在(该方法只能用于Service调用Service方法)
     * @param tableFrom
     * @param itemIds
     * @return
     */
    @Override
    public boolean getCheckDeletedConstraint(String tableFrom, String[] itemIds) {
        if (StringUtils.isEmpty(tableFrom) || null == itemIds)
            return true;

        List<SysDeleteConstraint> list = this.sysDeleteConstraintMapper
                .getDeletedConstraintList(tableFrom);//查询删除表的所有数据
        if (null == list || list.size() == 0) return false;

        for (SysDeleteConstraint entity : list) {//遍历这个表中的数据
            String tableTo = entity.getTableTo();//当前表关联的表名（用户表或者权限表）
            String tableToPkId = entity.getTableToPkid();//表的外键
            String field = entity.getConstraintField();
            for (String str : itemIds) {	//items的list集合是查询出的关于这个要删除的对象的关联的主键
                String itemId = str;
                Object object = sysDeleteConstraintMapper.checkDeletedConstraint(tableTo, tableToPkId, field, itemId);
                if (null != object) return true;
            }
        }
        return false;
    }
}

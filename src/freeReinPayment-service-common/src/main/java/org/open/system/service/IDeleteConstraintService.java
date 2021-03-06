package org.open.system.service;


/**
 * Created by lenovo on 2017/8/8.
 */
public interface IDeleteConstraintService {

    /***
     * 检测删除约束是否存在(该方法只能用于Service调用Service方法)
     * @param tableFrom
     * @param itemIds
     * @return
     */
    boolean getCheckDeletedConstraint(String tableFrom, String[] itemIds);
}

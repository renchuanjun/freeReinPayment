package org.open.system.service.impl;


import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.system.dao.SysOperategroupMapper;
import org.open.system.dao.SysPermissionOperategroupMapper;
import org.open.system.model.SysOperategroup;
import org.open.system.model.SysPermissionOperategroup;
import org.open.system.service.IDeleteConstraintService;
import org.open.system.service.IOperategroupService;
import org.open.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

/**
 * Created by lenovo on 2017/8/1.
 */
@Service(value = "iOperategroupService")
public class OperategroupServiceImpl implements IOperategroupService {

    @Autowired
    private SysOperategroupMapper sysOperategroupMapper;

    @Autowired
    private SysPermissionOperategroupMapper sysPermissionOperategroupMapper;

    @Autowired
    private IDeleteConstraintService iDeleteConstraintService;

    @Override
    public FQResult<PaginationSupport<SysOperategroup>> selectSysOperategroupByPage(SysOperategroup operategroup, PagerAndOrderByArgs args) {
    	FQResult<PaginationSupport<SysOperategroup>> fqResult = new FQResult<>();

        try {
            List<SysOperategroup> list = sysOperategroupMapper.selectSysOperategroupByPage(operategroup,args);
            int totalCount = sysOperategroupMapper.selectSysOperategroupByPageCount(operategroup);
            PaginationSupport<SysOperategroup> paginationSupport = new PaginationSupport<SysOperategroup>(list,totalCount,args.getPageSize(),args.getCurrentPage());
            fqResult.setResult(paginationSupport);
            fqResult.setSuccess(true);

        } catch (Exception e) {
        	fqResult.setException(e);
        }
        return fqResult;
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public FQResult<Object> saveSysOperategroup(SysOperategroup operategroup,String permissionIds,String hkPermissionIds) {
    	FQResult<Object> fqResult = new FQResult<>();

        try {
            String groupPermissionMd5 = MD5Utils.GetMD5Code((permissionIds+hkPermissionIds));
            if (StringUtils.isEmpty(operategroup.getGroupId())) {
                String groupId = UUID.randomUUID().toString();
                operategroup.setGroupId(groupId);
                operategroup.setGroupPermissionMd5(groupPermissionMd5);
                this.sysOperategroupMapper.insertSelective(operategroup);

                insert(groupId,operategroup,permissionIds,hkPermissionIds);
            }
            else {

                String groupId = operategroup.getGroupId();
                SysOperategroup operategroup1 = this.sysOperategroupMapper.selectByPrimaryKey(groupId);

                //System.out.println("md5"+operategroup1.getGroupPermissionMd5());
                //检测如果md5值相等，代表没有更改过权限树
                if (!groupPermissionMd5.equals(operategroup1.getGroupPermissionMd5())){
                    //先删除旧的权限关联

                    SysPermissionOperategroup permissionOperategroup = new SysPermissionOperategroup();
                    permissionOperategroup.setGroupId(groupId);
                    permissionOperategroup.setForeBackType(operategroup.getForeBackType());
                    permissionOperategroup.setIsDeleted(Byte.valueOf("1"));
                    permissionOperategroup.setUpdateBy(operategroup.getUpdateBy());
                    permissionOperategroup.setUpdateOn(operategroup.getUpdateOn());
                    permissionOperategroup.setUpdateByName(operategroup.getUpdateByName());
                    this.sysPermissionOperategroupMapper.deleteSysPermissionOperategroup(permissionOperategroup);

                    insert(groupId,operategroup,permissionIds,hkPermissionIds);
                }

                operategroup.setGroupPermissionMd5(groupPermissionMd5);
                this.sysOperategroupMapper.updateByPrimaryKeySelective(operategroup);


            }
            fqResult.setSuccess(true);
        } catch (Exception e) {
        	fqResult.setException(e);
            //手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return fqResult;
    }

    private void insert(String groupId,SysOperategroup operategroup,String permissionIds,String hkPermissionIds){
        //全选
        if (!StringUtils.isEmpty(permissionIds)) {
            String[] items = permissionIds.split("\\_");
            for (String item : items) {
                SysPermissionOperategroup permissionOperategroup = new SysPermissionOperategroup();
                permissionOperategroup.setGroupId(groupId);
                permissionOperategroup.setGroupRowId(UUID.randomUUID().toString());
                permissionOperategroup.setRowId(item);
                permissionOperategroup.setCheckStatus(Byte.valueOf("1"));
                permissionOperategroup.setForeBackType(operategroup.getForeBackType());
                permissionOperategroup.setCreateBy(operategroup.getUpdateBy());//该地方不是写错，是故意用更新字段填充创建字段
                permissionOperategroup.setCreateOn(operategroup.getUpdateOn());//该地方不是写错，是故意用更新字段填充创建字段
                permissionOperategroup.setCreateByName(operategroup.getUpdateByName());//该地方不是写错，是故意用更新字段填充创建字段
                permissionOperategroup.setUpdateBy(operategroup.getUpdateBy());
                permissionOperategroup.setUpdateOn(operategroup.getUpdateOn());
                permissionOperategroup.setUpdateByName(operategroup.getUpdateByName());
                this.sysPermissionOperategroupMapper.insertSelective(permissionOperategroup);
            }
        }

        //半选
        if (!StringUtils.isEmpty(hkPermissionIds)) {
            String[] items = hkPermissionIds.split("\\_");
            for (String item : items) {
                SysPermissionOperategroup permissionOperategroup = new SysPermissionOperategroup();
                permissionOperategroup.setGroupId(groupId);
                permissionOperategroup.setGroupRowId(UUID.randomUUID().toString());
                permissionOperategroup.setRowId(item);
                permissionOperategroup.setCheckStatus(Byte.valueOf("2"));
                permissionOperategroup.setForeBackType(operategroup.getForeBackType());
                permissionOperategroup.setCreateBy(operategroup.getUpdateBy());//该地方不是写错，是故意用更新字段填充创建字段
                permissionOperategroup.setCreateOn(operategroup.getUpdateOn());//该地方不是写错，是故意用更新字段填充创建字段
                permissionOperategroup.setCreateByName(operategroup.getUpdateByName());//该地方不是写错，是故意用更新字段填充创建字段
                permissionOperategroup.setUpdateBy(operategroup.getUpdateBy());
                permissionOperategroup.setUpdateOn(operategroup.getUpdateOn());
                permissionOperategroup.setUpdateByName(operategroup.getUpdateByName());
                this.sysPermissionOperategroupMapper.insertSelective(permissionOperategroup);
            }
        }
    }

    @Override
    public FQResult<SysOperategroup> getSysOperategroup(String id) {
    	FQResult<SysOperategroup> fqResult = new FQResult<>();
        try {
            SysOperategroup operategroup = this.sysOperategroupMapper.selectByPrimaryKey(id);
            fqResult.setResult(operategroup);
            fqResult.setSuccess(true);
        } catch (Exception e) {
        	fqResult.setException(e);
        }
        return fqResult;
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public FQResult<Object> deleteSysOperategroup(List<SysOperategroup> list) {
    	FQResult<Object> fqResult = new FQResult<>();
        try {
            int count = list.size();
            String[] itemsid = new String[count];
            for (int i=0;i<count;i++){
                itemsid[i]=list.get(i).getGroupId();
            }

            boolean flag = this.iDeleteConstraintService.getCheckDeletedConstraint("sys_operategroup",itemsid);
            if (flag) {
            	fqResult.setDetailInfo("删除项已经使用，不允许删除");
                return fqResult;
            }

            for (SysOperategroup operategroup:list) {

                this.sysOperategroupMapper.updateByPrimaryKeySelective(operategroup);
            }
            fqResult.setSuccess(true);
        } catch (Exception e) {
        	fqResult.setException(e);
            //手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return fqResult;
    }

    @Override
    public FQResult<List<SysOperategroup>> getSysOperategroupToRole(Integer foreBackType, String id) {
        FQResult<List<SysOperategroup>> fqResult = new FQResult<>();
        try {
            List<SysOperategroup> list = this.sysOperategroupMapper.selectSysOperategroupToRole(foreBackType,id);
            fqResult.setResult(list);

            fqResult.setSuccess(true);
        } catch (Exception e) {
        	fqResult.setException(e);
        }
        return fqResult;
    }
}

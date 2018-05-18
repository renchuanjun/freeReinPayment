package org.open.system.service.impl;


import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.system.dao.SysDatapermissionMapper;
import org.open.system.dao.SysRoleMapper;
import org.open.system.dao.SysUserRoleMapper;
import org.open.system.model.SysDatapermission;
import org.open.system.model.SysRole;
import org.open.system.model.SysUserRole;
import org.open.system.service.IDeleteConstraintService;
import org.open.system.service.IRoleService;
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
@Service(value = "iRoleService")
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysDatapermissionMapper sysDatapermissionMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private IDeleteConstraintService iDeleteConstraintService;

    @Override
    public FQResult<PaginationSupport<SysRole>> selectSysRoleByPage(SysRole role, PagerAndOrderByArgs args) {
        FQResult<PaginationSupport<SysRole>> fqResult = new FQResult<>();

        try {
            List<SysRole> list = sysRoleMapper.selectSysRoleByPage(role,args);
            int totalCount = sysRoleMapper.selectSysRoleByPageCount(role);
            PaginationSupport<SysRole> paginationSupport = new PaginationSupport<SysRole>(list,totalCount,args.getPageSize(),args.getCurrentPage());
            fqResult.setResult(paginationSupport);
            fqResult.setSuccess(true);

        } catch (Exception e) {
        	fqResult.setException(e);

        }

        return fqResult;
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public FQResult<Object> saveSysRole(SysRole role,List<SysDatapermission> datapermissionList, List<SysUserRole> userRoleList) {
        FQResult<Object> fqResult = new FQResult<>();
        try {
            if (StringUtils.isEmpty(role.getRoleId())) {
                String uuid = UUID.randomUUID().toString();
                role.setRoleId(uuid);
                String roleDatapermissionMd5 = MD5Utils.GetMD5CodeByObj(datapermissionList);
                String roleUserMd5 = MD5Utils.GetMD5CodeByObj(userRoleList);
                role.setRoleDatapermissionMd5(roleDatapermissionMd5);
                role.setRoleUserMd5(roleUserMd5);
                this.sysRoleMapper.insertSelective(role);

                insert(role,datapermissionList,userRoleList);

            }
            else {
                String roleDatapermissionMd5 = MD5Utils.GetMD5CodeByObj(datapermissionList);
                String roleUserMd5 = MD5Utils.GetMD5CodeByObj(userRoleList);

                SysRole role1 = this.sysRoleMapper.selectByPrimaryKey(role.getRoleId());

                //检测是否数据权限有变化
                if (!roleDatapermissionMd5.equals(role1.getRoleDatapermissionMd5())){

                    SysDatapermission datapermission = new SysDatapermission();
                    datapermission.setRoleId(role.getRoleId());
                    datapermission.setForeBackType(role.getForeBackType());
                    datapermission.setIsDeleted(Byte.valueOf("1"));
                    datapermission.setUpdateBy(role.getUpdateBy());
                    datapermission.setUpdateOn(role.getUpdateOn());
                    datapermission.setUpdateByName(role.getUpdateByName());
                    this.sysDatapermissionMapper.deleteSysDatapermissionToRole(datapermission);

                    insert(role,datapermissionList,null);
                }

                //检测用户是否有变化
                if (!roleUserMd5.equals(role1.getRoleUserMd5())){
                    SysUserRole userRole = new SysUserRole();
                    userRole.setRoleId(role.getRoleId());
                    userRole.setForeBackType(role.getForeBackType());
                    userRole.setIsDeleted(Byte.valueOf("1"));
                    userRole.setUpdateBy(role.getUpdateBy());
                    userRole.setUpdateOn(role.getUpdateOn());
                    userRole.setUpdateByName(role.getUpdateByName());
                    this.sysUserRoleMapper.deleteSysUserRoleToRole(userRole);

                    insert(role,null,userRoleList);
                }

                role.setRoleDatapermissionMd5(roleDatapermissionMd5);
                role.setRoleUserMd5(roleUserMd5);
                this.sysRoleMapper.updateByPrimaryKeySelective(role);


            }
            fqResult.setSuccess(true);
        } catch (Exception e) {
        	fqResult.setException(e);
            //手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return fqResult;
    }

    private void insert(SysRole role,List<SysDatapermission> datapermissionList, List<SysUserRole> userRoleList){
        //数据权限
        if (null != datapermissionList && datapermissionList.size() > 0){
            for (SysDatapermission datapermission:datapermissionList) {
                String uuid1 = UUID.randomUUID().toString();
                datapermission.setDpId(uuid1);
                datapermission.setRoleId(role.getRoleId());
                datapermission.setForeBackType(role.getForeBackType());
                datapermission.setCreateBy(role.getUpdateBy());//该地方不是写错，是故意用更新字段填充创建字段
                datapermission.setCreateOn(role.getUpdateOn());//该地方不是写错，是故意用更新字段填充创建字段
                datapermission.setCreateByName(role.getUpdateByName());//该地方不是写错，是故意用更新字段填充创建字段
                datapermission.setUpdateBy(role.getUpdateBy());
                datapermission.setUpdateOn(role.getUpdateOn());
                datapermission.setUpdateByName(role.getUpdateByName());
                this.sysDatapermissionMapper.insertSelective(datapermission);
            }
        }

        //用户
        if (null != userRoleList && userRoleList.size() > 0){
            for (SysUserRole userRole:userRoleList) {
                String uuid1 = UUID.randomUUID().toString();
                userRole.setRoleUserId(uuid1);
                userRole.setRoleId(role.getRoleId());
                userRole.setForeBackType(role.getForeBackType());
                userRole.setCreateBy(role.getUpdateBy());//该地方不是写错，是故意用更新字段填充创建字段
                userRole.setCreateOn(role.getUpdateOn());//该地方不是写错，是故意用更新字段填充创建字段
                userRole.setCreateByName(role.getUpdateByName());//该地方不是写错，是故意用更新字段填充创建字段
                userRole.setUpdateBy(role.getUpdateBy());
                userRole.setUpdateOn(role.getUpdateOn());
                userRole.setUpdateByName(role.getUpdateByName());
                this.sysUserRoleMapper.insertSelective(userRole);
            }
        }
    }

    @Override
    public FQResult<SysRole> getSysRole(String id) {
        FQResult<SysRole> fqResult = new FQResult<>();
        try {
            SysRole role = this.sysRoleMapper.selectByPrimaryKey(id);
            fqResult.setResult(role);
            fqResult.setSuccess(true);
        } catch (Exception e) {
        	fqResult.setException(e);
        }
        return fqResult;
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public FQResult<Object> deleteSysRole(List<SysRole> list) {
        FQResult<Object> fqResult = new FQResult<>();
        try {
            int count = list.size();
            String[] itemsid = new String[count];
            for (int i=0;i<count;i++){
                itemsid[i]=list.get(i).getRoleId();
            }

            boolean flag = this.iDeleteConstraintService.getCheckDeletedConstraint("sys_role",itemsid);
            if (flag) {
            	fqResult.setDetailInfo("删除项已经使用，不允许删除");
                return fqResult;
            }

            for (SysRole role:list) {

                this.sysRoleMapper.updateByPrimaryKeySelective(role);
            }
            fqResult.setSuccess(true);
        } catch (Exception e) {
        	fqResult.setException(e);
            //手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return fqResult;
    }
}

package org.open.system.service.impl;


import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.model.SubFQResult;
import org.open.system.dao.SysDatapermissionMapper;
import org.open.system.dao.SysPermissionMapper;
import org.open.system.dao.SysRoleMapper;
import org.open.system.dao.SysUserMapper;
import org.open.system.model.SysDatapermission;
import org.open.system.model.SysPermission;
import org.open.system.model.SysRole;
import org.open.system.model.SysUser;
import org.open.system.service.IDeleteConstraintService;
import org.open.system.service.IUserService;
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
@Service(value = "iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysDatapermissionMapper sysDatapermissionMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private IDeleteConstraintService iDeleteConstraintService;

    @Override
    public FQResult<PaginationSupport<SysUser>> selectSysUserByPage(SysUser user, PagerAndOrderByArgs args) {
    	FQResult<PaginationSupport<SysUser>> fqResult = new FQResult<>();

        try {
            List<SysUser> list = sysUserMapper.selectSysUserByPage(user,args);
            int totalCount = sysUserMapper.selectSysUserByPageCount(user);
            PaginationSupport<SysUser> paginationSupport = new PaginationSupport<SysUser>(list,totalCount,args.getPageSize(),args.getCurrentPage());
            fqResult.setResult(paginationSupport);
            fqResult.setSuccess(true);
        } catch (Exception e) {
        	fqResult.setException(e);
        }
        return fqResult;
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public FQResult<Object> saveSysUser(SysUser user) {
    	FQResult<Object> fqResult = new FQResult<>();
        try {
            if (StringUtils.isEmpty(user.getUserId())) {
                String uuid = UUID.randomUUID().toString();
                user.setUserId(uuid);
                this.sysUserMapper.insertSelective(user);
            }
            else {
                this.sysUserMapper.updateByPrimaryKeySelective(user);
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
    public FQResult<SysUser> getSysUser(String id) {
    	FQResult<SysUser> fqResult = new FQResult<>();
        try {
            SysUser user = this.sysUserMapper.selectByPrimaryKey(id);
            fqResult.setResult(user);
            fqResult.setSuccess(true);
        } catch (Exception e) {
        	fqResult.setException(e);
        }
        return fqResult;
    }

    @Override
    public FQResult<Object> deleteSysUser(List<SysUser> list) {
    	FQResult<Object> fqResult = new FQResult<>();
        try {
            int count = list.size();
            String[] itemsid = new String[count];
            for (int i=0;i<count;i++){
                itemsid[i]=list.get(i).getUserId();
            }
            boolean flag = this.iDeleteConstraintService.getCheckDeletedConstraint("sys_user",itemsid);
            if (flag) {
            	fqResult.setDetailInfo("删除项已经使用，不允许删除");
                return fqResult;
            }
            for (SysUser user:list) {
                this.sysUserMapper.updateByPrimaryKeySelective(user);
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
    public FQResult<SubFQResult<SysUser,SubFQResult<List<SysDatapermission>,List<SysPermission>>>> login(SysUser user) {
    	FQResult<SubFQResult<SysUser,SubFQResult<List<SysDatapermission>,List<SysPermission>>>> fqResult = new FQResult<>();
        SubFQResult<SysUser,SubFQResult<List<SysDatapermission>,List<SysPermission>>> hnaResult1 = new SubFQResult<>();
        SubFQResult<List<SysDatapermission>,List<SysPermission>> hnaResult2 = new SubFQResult<>();
        fqResult.setResult(hnaResult1);
        hnaResult1.setUserData(hnaResult2);
        try {
            //验证用户名与密码是否正确
            List<SysUser> list = this.sysUserMapper.selectSysUser(user);
            SysUser user1 = null;
            if (null != list && list.size() == 1){
                user1 = list.get(0);
                hnaResult1.setResult(user1);
                fqResult.setSuccess(true);
                hnaResult1.setSuccess(true);
                //获取角色数组
                List<SysUser> sysUserList = this.sysUserMapper.selectRoleBySysUser(user.getForeBackType(),user1.getUserId());
                String[] items = null;
                if (null != sysUserList && sysUserList.size() > 0){
                    items = new String[sysUserList.size()];
                    for (int i=0;i<sysUserList.size();i++){
                        items[i] = sysUserList.get(i).getRoleId();
                    }
                }

                if (null != items && items.length > 0) {
                    //获取数据权限ID集合
                    List<SysDatapermission> datapermissionList = this.sysDatapermissionMapper.getDataIdByRole(items, user.getForeBackType());
                    hnaResult2.setResult(datapermissionList);
                    String[] items1 = null;
                    //获取操作组ID集合
                    List<SysRole> sysRoleList = this.sysRoleMapper.selectGroupIdByRole(items, user.getForeBackType());
                    if (null != sysRoleList && sysRoleList.size() > 0){
                        items1 = new String[sysRoleList.size()];
                        for (int i=0;i<sysRoleList.size();i++){
                            items1[i] = sysRoleList.get(i).getGroupId();
                        }
                    }

                    if (null != items1 && items1.length > 0){
                        //获取操作权限集合
                        List<SysPermission> permissionList = this.sysPermissionMapper.selectSysPermissionByOperateGroup(items1, user.getForeBackType());
                        hnaResult2.setUserData(permissionList);
                    }

                    hnaResult2.setSuccess(true);
                }

            }
            else if (null != list && list.size() > 0){
            	fqResult.setDetailInfo("存在多个相同用户，请联系管理员");
                return fqResult;
            }


        } catch (Exception e) {
        	fqResult.setException(e);
        }
        return fqResult;
    }
    
    /**
     * 查询用户是否存在
     */
	@Override
	public FQResult<Object> selectSysUser(SysUser user) {
		FQResult<Object> fqResult = new FQResult<Object>();
		try {
			int count = this.sysUserMapper.checkUserNameOnly(user.getUserName());
            if (count > 0) {
            	fqResult.setDetailInfo("该帐号已经存在，请更换帐号");
                return fqResult;
            }
            fqResult.setSuccess(true);
		} catch (Exception e) {
			fqResult.setException(e);
		}
		return fqResult;
	}
}

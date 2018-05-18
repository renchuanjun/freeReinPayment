package org.open.system.service.impl;


import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.system.dao.SysOrganMapper;
import org.open.system.model.SysOrgan;
import org.open.system.service.IDeleteConstraintService;
import org.open.system.service.IOrganService;
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
@Service(value = "iOrganService")
public class OrganServiceImpl implements IOrganService {

    @Autowired
    private SysOrganMapper sysOrganMapper;

    @Autowired
    private IDeleteConstraintService iDeleteConstraintService;

    @Override
    public FQResult<List<SysOrgan>> getOrganByAll(Integer foreBackType) {
        FQResult<List<SysOrgan>> fqResult = new FQResult<>();
        try {
            List<SysOrgan> list = sysOrganMapper.getOrganByAll(foreBackType);
            fqResult.setResult(list);
            fqResult.setSuccess(true);
        } catch (Exception e) {
        	fqResult.setException(e);
        }
        return fqResult;
    }

    @Override
    public FQResult<PaginationSupport<SysOrgan>> selectSysOrganByPage(SysOrgan organ, PagerAndOrderByArgs args) {
        FQResult<PaginationSupport<SysOrgan>> fqResult = new FQResult<>();

        try {
            List<SysOrgan> list = sysOrganMapper.selectSysOrganByPage(organ,args);
            int totalCount = sysOrganMapper.selectSysOrganByPageCount(organ);
            PaginationSupport<SysOrgan> paginationSupport = new PaginationSupport<SysOrgan>(list,totalCount,args.getPageSize(),args.getCurrentPage());
            fqResult.setResult(paginationSupport);
            fqResult.setSuccess(true);

        } catch (Exception e) {
        	fqResult.setException(e);

        }

        return fqResult;
    }

    /***
     * 添加或者修改数据
     * @param organ
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    @Override
    public FQResult<Object> saveSysOrgan(SysOrgan organ) {
        FQResult<Object> fqResult = new FQResult<>();
        try {
            if (null == organ.getOrganId()) {
                this.sysOrganMapper.insertSelective(organ);
                Integer parentId = organ.getParentId();
                SysOrgan organParent = this.sysOrganMapper.selectByPrimaryKey(parentId);
                String organFullId= organParent.getOrganFullId()+organ.getOrganId()+"-";
                organ.setOrganFullId(organFullId);
                this.sysOrganMapper.updateByPrimaryKeySelective(organ);
            }
            else {
                this.sysOrganMapper.updateByPrimaryKeySelective(organ);
            }
            fqResult.setSuccess(true);
        } catch (Exception e) {
        	fqResult.setException(e);
            //手动事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return fqResult;
    }

    /***
     * 通过主键获取单个实体
     * @param id
     * @return
     */
    @Override
    public FQResult<SysOrgan> getSysOrgan(Integer id) {
        FQResult<SysOrgan> fqResult = new FQResult<>();
        try {
            SysOrgan organ = this.sysOrganMapper.selectByPrimaryKey(id);
            fqResult.setResult(organ);
            fqResult.setSuccess(true);
        } catch (Exception e) {
        	fqResult.setException(e);
        }
        return fqResult;
    }

    /**
     * 删除(假删)
     * @param list
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    @Override
    public FQResult<Object> deleteSysOrgan(List<SysOrgan> list) {
        FQResult<Object> fqResult = new FQResult<>();
        try {
            int count = list.size();
            String[] itemsid = new String[count];
            for (int i=0;i<count;i++){
                itemsid[i]=list.get(i).getOrganId().toString();
            }

            boolean flag = this.iDeleteConstraintService.getCheckDeletedConstraint("sys_organ",itemsid);
            if (flag) {
            	fqResult.setDetailInfo("删除项已经使用，不允许删除");
                return fqResult;
            }

            for (SysOrgan organ:list) {

                this.sysOrganMapper.updateByPrimaryKeySelective(organ);
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

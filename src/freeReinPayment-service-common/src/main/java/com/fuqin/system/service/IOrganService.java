package com.fuqin.system.service;


import com.fuqin.model.FQResult;
import com.fuqin.model.PagerAndOrderByArgs;
import com.fuqin.model.PaginationSupport;
import com.fuqin.system.model.SysOrgan;

import java.util.List;

/**
 * Created by lenovo on 2017/8/1.
 */
public interface IOrganService {

    /***
     * 加载组织机构树
     * @return
     */
	FQResult<List<SysOrgan>> getOrganByAll(Integer foreBackType);

	FQResult<PaginationSupport<SysOrgan>> selectSysOrganByPage(SysOrgan organ, PagerAndOrderByArgs args);

	FQResult<Object> saveSysOrgan(SysOrgan organ);

	FQResult<SysOrgan> getSysOrgan(Integer id);

	FQResult<Object> deleteSysOrgan(List<SysOrgan> list);
}

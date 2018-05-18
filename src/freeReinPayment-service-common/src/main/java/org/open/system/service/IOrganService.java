package org.open.system.service;


import java.util.List;

import org.open.model.FQResult;
import org.open.model.PagerAndOrderByArgs;
import org.open.model.PaginationSupport;
import org.open.system.model.SysOrgan;

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

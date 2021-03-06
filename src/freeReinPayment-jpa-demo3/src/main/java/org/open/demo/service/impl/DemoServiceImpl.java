package org.open.demo.service.impl;

import org.open.annotation.MyTransactional;
import org.open.enums.TransactionalRoleEnum;
import org.open.demo.service.IDemoService;
import org.open.model.FQResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 任传君
 * @create 2018-08-01 15:59
 **/
@Service(value = "iDemoService")
public class DemoServiceImpl implements IDemoService {

    @Override
    @Transactional(rollbackFor=Exception.class)
    @MyTransactional(destination = "demo" ,role = TransactionalRoleEnum.LOCAL)
    public FQResult<Object> saveDemo(String id) {
        FQResult<Object> fqResult = new FQResult<>();
        try {
            fqResult.setResult("demo1返回结果 :" + id);
            fqResult.setSuccess(true);
        }catch (Exception e){
            fqResult.setException(e);
        }
        return fqResult;
    }
}

package org.open.demo.service.impl;

import org.open.annotation.MyTransactional;
import org.open.demo.dao.Table1Mapper;
import org.open.demo.model.Table1;
import org.open.enums.TransactionalRoleEnum;
import org.open.demo.service.IDemoService;
import org.open.model.FQResult;
import org.open.utils.JavaRowLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.math.BigDecimal;

/**
 * @author 任传君
 * @create 2018-08-01 15:59
 **/
@Service(value = "iDemoService")
public class DemoServiceImpl implements IDemoService {

    @Autowired
    private PlatformTransactionManager transactionManager;


    @Autowired
    private Table1Mapper table1Mapper;

    @Override
    public FQResult<Object> saveDemo(String id) {
        FQResult<Object> fqResult = new FQResult<>();
        JavaRowLock javaRowLock = JavaRowLock.getJavaRowLock();
        System.out.println(javaRowLock.toString());
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            try {
                Table1 table1 = new Table1();
                table1.setId(Integer.valueOf(id));
                javaRowLock.lock(id);
                Table1 table1_1 = this.table1Mapper.selectByPrimaryKey(Integer.valueOf(id));
                BigDecimal decimal = table1_1.getAge();
                BigDecimal age = new BigDecimal(1);
                age = age.add(decimal);
                table1.setAge(age);
                table1Mapper.updateByPrimaryKeySelective(table1);
                fqResult.setResult("demo1返回结果 :" + id);
                fqResult.setSuccess(true);
                transactionManager.commit(status);
            }finally {
                javaRowLock.unLock(id);
            }
        }catch (Exception e){
            fqResult.setException(e);
            transactionManager.rollback(status);
        }
        return fqResult;
    }
}

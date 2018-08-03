package org.open.bean;

import lombok.Data;

/**
 * @author 任传君
 * @create 2018-08-02 11:23
 **/
@Data
public class MyTransactionContext implements java.io.Serializable{

    private String transId;

    /**
     * 事务参与的角色.
     * {@linkplain org.open.enums.TransactionalRoleEnum}
     */
    private int role;

}

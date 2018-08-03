package org.open.annotation;

import org.open.enums.TransactionalRoleEnum;
import org.open.enums.TransactionalTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 任传君
 * @create 2018-07-27 16:54
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MyTransactional {

    /**
     * 消息队列的唯一标识
     * @return
     */
    String destination() default "";

    /**
     * 事务类型
     * @return
     */
    TransactionalTypeEnum[] transactionalType() default {};

    /**
     * 业务角色
     * @return
     */
    TransactionalRoleEnum[] role() default {};


}

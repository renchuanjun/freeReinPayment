package org.open.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


/**
 * @author 任传君
 * @create 2018-08-02 11:27
 **/
@Aspect
@Component
public abstract class AbstractMyTransactionAspect {

    private MyTransactionInterceptor myTransactionInterceptor;

    public void setMyTransactionInterceptor(MyTransactionInterceptor myTransactionInterceptor) {
        this.myTransactionInterceptor = myTransactionInterceptor;
    }

    /***
     * 切入点
     */
    @Pointcut("@annotation(org.open.annotation.MyTransactional)")
    public void myTransactionInterceptor() {

    }

    /***
     * @param joinPoint
     */
    @Around(value = "myTransactionInterceptor()" )
    public Object interceptMyAnnotationMethod(JoinPoint joinPoint)throws Exception {
        return myTransactionInterceptor.interceptor(joinPoint);
    }

    /**
     * spring bean Order.
     *
     * @return int
     */
    public abstract int getOrder();
}

package org.open.aop;

import org.aspectj.lang.JoinPoint;

public interface MyTransactionInterceptor {

    Object interceptor(JoinPoint joinPoint)throws Exception;
}

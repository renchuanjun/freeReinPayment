package org.open.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.open.model.FQResult;
import org.open.utils.LogTextUtils;
import org.springframework.stereotype.Component;

/**
 * Created by lenovo on 2017/5/31.
 */
@Aspect
@Component
public class ServiceErrorLogInterceptor {

    /***
     * 切入点
     */
    @Pointcut(value = "execution(public org.open.model.FQResult org.open..*.service.*.*(..))")
    public void pointCut() {

    }

    /**
     * 之前
     * @param joinPoint
     */
    @Before(value = "pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("_____doBefore");
    }

    /***
     * 之后
     * @param joinPoint
     */
    @After(value = "pointCut()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("______doAfter");
    }

    /***
     * 返回
     * @param joinPoint
     * @param returnValue
     */
    @AfterReturning(value = "pointCut()", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) {
        System.out.println("____afterReturning");
        if (returnValue instanceof FQResult) {
        	FQResult<?> result = (FQResult<?>) returnValue;

            Exception ex = result.getException();
            if (null != ex) {
                result.setSuccess(false);
                String methodPath = joinPoint.getTarget().getClass().getName()
                        + "." + joinPoint.getSignature().getName();
                //错误轨迹
                StringBuffer stringBuffer = new StringBuffer();
                StackTraceElement [] messages=ex.getStackTrace();
                int length=messages.length;
                for(int i=0;i<length;i++){
                    stringBuffer.append(messages[i].toString());
                    stringBuffer.append("\r\n");
                }
                LogTextUtils logger = LogTextUtils.getLogger();
                logger.wirteError(ex,new String[]{"\r\nService层异常方法名称:",methodPath});
            }

        }
    }
}

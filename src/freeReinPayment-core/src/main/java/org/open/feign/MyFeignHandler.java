package org.open.feign;


import feign.InvocationHandlerFactory;
import org.open.annotation.MyTransactional;

import feign.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author 任传君
 * @create 2018-08-02 16:31
 **/
public class MyFeignHandler implements InvocationHandler {

    private Target<?> target;

    private Map<Method, InvocationHandlerFactory.MethodHandler> handlers;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            final MyTransactional myth = method.getAnnotation(MyTransactional.class);
        }
        System.out.println("aaaaa");
        return null;
    }



    public void setTarget(final Target<?> target) {
        this.target = target;
    }

    public void setHandlers(final Map<Method, InvocationHandlerFactory.MethodHandler> handlers) {
        this.handlers = handlers;
    }
}

package org.open.feigns;


import feign.InvocationHandlerFactory;
import lombok.Data;
import org.open.annotation.MyTransactional;

import feign.Target;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * @author 任传君
 * @create 2018-08-02 16:31
 **/
@Data
@Component
public class MyFeignHandler implements InvocationHandler {

    private Target<?> target;

    private Map<Method, InvocationHandlerFactory.MethodHandler> handlers;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            System.err.println("进入MyFeignHandler if 判断");
            return method.invoke(this, args);
        }else{
            final MyTransactional myTransactional = method.getAnnotation(MyTransactional.class);
            if (Objects.isNull(myTransactional)) {
                return this.handlers.get(method).invoke(args);
            }
        }

        return this.handlers.get(method).invoke(args);
    }



    public void setTarget(final Target<?> target) {
        this.target = target;
    }

    public void setHandlers(final Map<Method, InvocationHandlerFactory.MethodHandler> handlers) {
        this.handlers = handlers;
    }
}

package com.example.bookStore.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yuanlei
 * @description
 * @date 2021-06-05
 */
public class ProxyFactory {
    private Object targetObject; // 目标对象
    private BeforeAdvice beforeAdvice; // 前置增强
    private AfterAdvice afterAdvice; // 后置增强
    public Object createProxy() {
        ClassLoader loader = this.getClass().getClassLoader();
        Class[] interfaces = targetObject.getClass().getInterfaces();
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(beforeAdvice != null) {
                    beforeAdvice.before();
                }
                Object result = method.invoke(targetObject, args);
                if(afterAdvice != null) {
                    afterAdvice.after();
                }
                return result;
            }
        };
        Object proxyObject = Proxy.newProxyInstance(loader, interfaces, h);
        return proxyObject;
    }
    public Object getTargetObject() {
        return targetObject;
    }
    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }
    public BeforeAdvice getBeforeAdvice() {
        return beforeAdvice;
    }

    public void setBeforeAdvice(BeforeAdvice beforeAdvice) {
        this.beforeAdvice = beforeAdvice;
    }

    public AfterAdvice getAfterAdvice() {
        return afterAdvice;
    }

    public void setAfterAdvice(AfterAdvice afterAdvice) {
        this.afterAdvice = afterAdvice;
    }
}

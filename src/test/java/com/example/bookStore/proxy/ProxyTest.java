package com.example.bookStore.proxy;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author yuanlei
 * @description
 * @date 2021-06-05
 */
public class ProxyTest {
    /**
     * @Author yuanlei
     * @Description //
     * @Date 11:33 2021/6/5
     * @Param []
     * @return void
     **/
    @Test
    public void testSubject(){
        Subject subject = new SubjectImpl();
        InvocationHandler proxyHandler = new ProxyHandler(subject);
        Subject proxyInstance = (Subject) Proxy.newProxyInstance(proxyHandler.getClass().getClassLoader(), subject.getClass().getInterfaces(), proxyHandler);
        proxyInstance.hello("world");
    }
    
    /**
     * @Author yuanlei
     * @Description //
     * @Date 11:34 2021/6/5
     * @Param []
     * @return void
     **/
    @Test
    public void testProxyFactory(){
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTargetObject(new SubjectImpl());
        proxyFactory.setBeforeAdvice(new BeforeAdvice(){
            @Override
            public void before() {
                System.out.println("Before invoke ");
            }
        });
        proxyFactory.setAfterAdvice(new AfterAdvice() {
            @Override
            public void after() {
                System.out.println("After invoke ");
            }
        });
        Subject subjectProxy = (Subject) proxyFactory.createProxy();
        subjectProxy.hello("yuanlei");
    }

    /**
     * @Author yuanlei
     * @Description //
     * @Date 14:55 2021/6/5
     * @Param []
     * @return void
     **/
    @Test
    public void testCGLIBsubject(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CGsubject.class);
        enhancer.setCallback(new HelloInterceptor());
        CGsubject cGsubject = (CGsubject) enhancer.create();
        cGsubject.sayHello();
    }
}

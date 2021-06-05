package com.example.bookStore.proxy;

/**
 * @author yuanlei
 * @description
 * @date 2021-06-05
 */
public class SubjectImpl implements Subject{
    @Override
    public void hello(String param) {
        System.out.println("hello  " + param);
    }
}

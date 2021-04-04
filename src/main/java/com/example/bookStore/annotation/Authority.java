package com.example.bookStore.annotation;

import java.lang.annotation.*;

/**
 * @author yuanlei
 * @description
 * @date 2021-03-29
 */
@Inherited
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Authority {
    boolean needLogin() default false;
}

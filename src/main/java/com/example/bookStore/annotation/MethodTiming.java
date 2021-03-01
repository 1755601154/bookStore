package com.example.bookStore.annotation;

import java.lang.annotation.*;

/**
 * @author yuanlei
 * @date 2020-11-24
 */
@Inherited
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodTiming {
    String value() default "cost:%sms";
}

package com.example.bookStore.database.annotation;

import java.lang.annotation.*;

/**
 * @author yuanlei
 * @description
 * @date 2020-11-24
 */


@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    /**
     * 切换数据源名称
     */
    String value() default "MASTER";
}

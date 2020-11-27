package com.example.bookStore.annotation;

import com.example.bookStore.datasource.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author yuanlei
 * @description DataSource注解的切面
 * @date 2020-11-24
 */
@Aspect
@Order(1)
@Component
@Slf4j
public class DataSourceAspect {

    @Around("@annotation(com.example.bookStore.annotation.DataSource)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        DataSource dataSource = method.getAnnotation(DataSource.class);
        if (dataSource != null) {
            DynamicDataSourceContextHolder.setDataSourceType(dataSource.value());
        }else {
            dataSource = signature.getClass().getAnnotation(DataSource.class);
            if(dataSource != null){
                DynamicDataSourceContextHolder.setDataSourceType(dataSource.value());
            }
        }
        try {
            return point.proceed();
        } finally {
            // 销毁数据源 在执行方法之后
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }
}

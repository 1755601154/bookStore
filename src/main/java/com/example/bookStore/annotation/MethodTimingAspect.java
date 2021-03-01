package com.example.bookStore.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author yuanlei
 * @date 2020-11-24
 */
@Aspect
@Component
@Slf4j
public class MethodTimingAspect {
    @Around("@annotation(com.example.bookStore.annotation.MethodTiming)")
    public Object log(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        MethodTiming methodTiming = method.getAnnotation(MethodTiming.class);
        long startTime = System.currentTimeMillis();
        Object result = point.proceed();
        long endTime = System.currentTimeMillis();
        log.info(String.format(methodTiming.value(), (endTime - startTime)));
        return result;
    }
}

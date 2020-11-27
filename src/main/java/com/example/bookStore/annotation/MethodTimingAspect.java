package com.example.bookStore.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author yuanlei
 * @date 2020-11-24
 */
@Aspect
@Component
@Slf4j
public class MethodTimingAspect {
    @Around("@annotation(com.example.bookStore.annotation.MethodTiming)")
    public Object log(ProceedingJoinPoint point) throws Throwable
    {
        log.info(MethodSignature.class.cast(point.getSignature()).getMethod().getName()+"方法计时开始!");
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        log.info(MethodSignature.class.cast(point.getSignature()).getMethod().getName()+"方法计时结束!");
        log.info("className={}, methodName={}, timeMs={},threadId={}",new Object[]{
                MethodSignature.class.cast(point.getSignature()).getDeclaringTypeName(),
                MethodSignature.class.cast(point.getSignature()).getMethod().getName(),
                System.currentTimeMillis() - start,
                Thread.currentThread().getId()}
        );
        return result;
    }
}

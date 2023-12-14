package com.example.sbpro.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;

@Component
@Aspect
@Slf4j
public class LimitAop {
    private final ConcurrentMap<String, Semaphore> semaphoreMap = new ConcurrentHashMap<>();

    @Around("@annotation(limit)")
    public Object limitAccess(ProceedingJoinPoint joinPoint, Limit limit) throws Throwable {
        MethodSignature methodName = (MethodSignature) joinPoint.getSignature();
        Semaphore semaphore = semaphoreMap.computeIfAbsent(methodName.getMethod().getName(), k -> new Semaphore(limit.value()));
        if (semaphore.tryAcquire()) {
            try {
                return joinPoint.proceed();
            } finally {
                semaphore.release();
            }
        } else {
            throw new RuntimeException(limit.msg());
        }
    }
}



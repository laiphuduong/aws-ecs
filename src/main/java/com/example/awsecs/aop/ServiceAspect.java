package com.example.awsecs.aop;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class ServiceAspect {
    @Pointcut("within(com.example.awsecs.service.*)")
    public void servicePointCut(){};
    @After(value = "servicePointCut()")
    public void afterReturn(JoinPoint joinPoint) throws Throwable {
        log.info("Value return in service is " + joinPoint.getSignature().getName());
    }

    @Before(value = "servicePointCut()")
    public void beforeCall(JoinPoint joinPoint) throws Throwable {
        log.info("Call function in service layer: " + joinPoint.getSignature().getName());
    }
}

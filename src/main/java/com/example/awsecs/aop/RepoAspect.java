package com.example.awsecs.aop;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class RepoAspect {
    @Pointcut("execution(* com.example.awsecs.repository.*.*(..))")
    public void repoPointCut(){};

//    @Pointcut("within(com.example.awsecs.repository.*)")
//    public void repoPointCut(){};

    @AfterReturning(value = "repoPointCut()", returning = "data")
    public void afterReturn(JoinPoint joinPoint, Object data) throws Throwable {
        log.info("Value return in repo is " + joinPoint.getSignature().getName());
//        if (data instanceof List) {
//            var dataConvert = (ArrayList) data;
//            dataConvert.forEach(x -> log.info(x.toString()));
//        }
        log.info("Data is: " + data.toString());
    }
}

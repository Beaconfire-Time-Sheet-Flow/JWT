package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAspect {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("com.example.aop.aop.PointCuts.thisUserService()")
    public void beforeAdvice(JoinPoint joinPoint){
        //send request
        log.warn("this is before advice:" + joinPoint.getSignature());
    }

    @Around("com.example.aop.aop.PointCuts.inDAOLayer()")
    public Object executionTimeAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        log.warn("before execution");
        Object res = joinPoint.proceed();
        long executionTime = System.currentTimeMillis()-startTime;
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        log.warn(className+"."+methodName+" execution time: "+executionTime+" ms "+"result: "+res);
        return res;
    }

    @After("com.example.aop.aop.PointCuts.inController()")
    public void inControllerAfterAdvice(JoinPoint joinPoint){
        log.warn("after advice: "+joinPoint.getSignature());
    }

    @AfterReturning(value = "com.example.aop.aop.PointCuts.methodWithinRestController()", returning = "res")
    public void afterReturningAdvice(Object res){
        //get response
        log.warn("After Returning: " + res.toString());
    }

}

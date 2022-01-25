package com.example.aop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointCuts {

    @Pointcut("within(com.example.aop.DAO..*)")
    public void inDAOLayer(){}

    @Pointcut("this(com.example.aop.service.UserService)")
    public void thisUserService(){}

    @Pointcut("bean(*Controller)")
    public void inController(){}

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void methodWithinRestController() {}
}

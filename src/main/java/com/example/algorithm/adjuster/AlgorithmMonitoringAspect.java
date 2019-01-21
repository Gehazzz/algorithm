package com.example.algorithm.adjuster;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AlgorithmMonitoringAspect {
    @Autowired
    private State state;

    @Before("execution(* com.example.algorithm.service.AlgorithmServiceImpl.run(..))")
    public void registerAlgorithmRun(JoinPoint joinPoint) {
        state.incrementRunningNow();
    }
    @After("execution(* com.example.algorithm.service.AlgorithmServiceImpl.run(..))")
    public void unRegisterAlgorithmRun(JoinPoint joinPoint) {
        state.decrementRunningNow();
    }
}

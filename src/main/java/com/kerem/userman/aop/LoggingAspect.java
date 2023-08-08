package com.kerem.userman.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.kerem.userman.controller.*Controller.*(..))")
    public void logControllerBeforeMethods(JoinPoint joinPoint) {
    	String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String httpMethod = request.getMethod();
        StringBuilder argString = new StringBuilder();
        for (Object arg : args) {
            argString.append(arg).append(", ");
        }
        
        System.out.println("Executing [" + httpMethod + "]" + className + "." + methodName + "(), Arguments: " + argString);
    }
    
    @AfterReturning(pointcut = "execution(* com.kerem.userman.controller.*Controller.*(..))", returning = "result")
    public void logControllerAfterMethods(JoinPoint joinPoint, Object result) {
    	String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String httpMethod = request.getMethod();

        System.out.println("Executed [" + httpMethod + "]" + className + "." + methodName + "(), Go page: " + result);
    }
    
    @AfterThrowing(pointcut = "execution(* com.kerem.userman.service.impl.*.*(..))", throwing = "exception")
    public void handleServiceExceptions(JoinPoint joinPoint, Exception exception) {
        // Handle the exception here
        System.out.println("Exception caught: " + exception.getMessage());
    }
}
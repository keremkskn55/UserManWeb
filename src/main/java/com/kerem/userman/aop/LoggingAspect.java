package com.kerem.userman.aop;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingAspect {
	
	static Logger log = Logger.getRootLogger();
	
	@Pointcut("execution(* com.kerem.userman.controller.*Controller.*(..))")
	public void controllerPointcut( ) {
		// NO-OP
	}

    @Before("controllerPointcut()")
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
        
        log.info("Executing [" + httpMethod + "]" + className + "." + methodName + "(), Arguments: " + argString);
    }
    
    @AfterReturning(pointcut = "controllerPointcut()", returning = "result")
    public void logControllerAfterMethods(JoinPoint joinPoint, Object result) {
    	String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String httpMethod = request.getMethod();

        log.info("Executed [" + httpMethod + "]" + className + "." + methodName + "(), Go page: " + result);
    }
    
    @Around("controllerPointcut()")
    public Object handleControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return (String) joinPoint.proceed();
        } catch (Exception exception) {
            String className = joinPoint.getTarget().getClass().getSimpleName();
            String methodName = joinPoint.getSignature().getName();
            log.error("Exception caught on: " + className + "." + methodName + ", Error: " + exception.getMessage());
            
            return "error";
        }
    }
}
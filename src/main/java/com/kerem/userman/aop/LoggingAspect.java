package com.kerem.userman.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingAspect {
	
	static Logger log = Logger.getRootLogger();
	
	@Pointcut("execution(* com.kerem.userman.controller.UserController.*(..))")
	public void userControllerPointcut( ) {
		// NO-OP
	}
	
	@Pointcut("execution(* com.kerem.userman.controller.RoleController.*(..))")
	public void roleControllerPointcut( ) {
		// NO-OP
	}

    @Before("userControllerPointcut() || roleControllerPointcut()")
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
    
    @AfterReturning(pointcut = "userControllerPointcut() || roleControllerPointcut()", returning = "result")
    public void logControllerAfterMethods(JoinPoint joinPoint, Object result) {
    	String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String httpMethod = request.getMethod();

        log.info("Executed [" + httpMethod + "]" + className + "." + methodName + "(), Go page: " + result);
    }
    
    @Around("userControllerPointcut() || roleControllerPointcut()")
    public Object handleControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
        	ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attributes.getRequest().getSession();
            String jwtToken = (String) session.getAttribute("jwtToken");
            
            if (jwtToken == null) {
            	return "redirect:/auth/signIn";
            }
            
            return (String) joinPoint.proceed();
        } catch (HttpClientErrorException exception) {
            String className = joinPoint.getTarget().getClass().getSimpleName();
            String methodName = joinPoint.getSignature().getName();
            log.error("Exception caught on: " + className + "." + methodName + ", Error: " + exception.getMessage());
            
        	if (exception.getRawStatusCode() == 401) {
        		return "redirect:/auth/signIn";
        	}
            return "error";
        }
    }
    
}
package com.kerem.userman.aop;

import javax.servlet.http.HttpServletRequest;

import com.kerem.userman.holder.JwtHolder;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingAspect {
	
	static Logger log = Logger.getRootLogger();
	
	@Autowired
	private JwtHolder jwtHolder;
	
	@Pointcut("execution(* com.kerem.userman.controller.*Controller.*(..))")
	public void userControllerPointcut( ) {
		// NO-OP
	}

    @Before("userControllerPointcut()")
    public void userControllerBeforeMethods(JoinPoint joinPoint) {
    	beforeLog(joinPoint);
    }
    
    @AfterReturning(pointcut = "userControllerPointcut()", returning = "result")
    public Object logControllerAfterMethods(JoinPoint joinPoint, Object result) {
    	afterLog(joinPoint, result);
    	
        if (result instanceof ResponseEntity) {
            ResponseEntity<?> responseEntity = (ResponseEntity<?>) result;
            int responseCode = responseEntity.getStatusCodeValue();
            
            if (responseCode == HttpStatus.UNAUTHORIZED.value()) {
                // Redirect to the different page when response code is 401
                return "redirect:/auth/signIn";
            }
        }
        
        // Continue with the normal processing
        return result;
    }
    
    @Around("userControllerPointcut()")
    public Object handleControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
        	String token = jwtHolder.getJwtToken();
        	
        	if (token == null) {
        		return "token-checker";
        	}
        	
        	Object[] args = joinPoint.getArgs();

            boolean hasBearerToken = false;

            // Check if any argument starts with "Bearer "
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                if (arg instanceof String) {
                    String headerValue = (String) arg;
                    if (headerValue.startsWith("Bearer ")) {
                        hasBearerToken = true;
                        args[i] = "Bearer " + token;
                        break;
                    }
                }
            }

            // If no argument starts with "Bearer ", add a new "Bearer" token
            if (!hasBearerToken) {
                // Add "Bearer" token to headers
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
                // Replace "HttpHeaders" with the appropriate class representing your headers
                args[args.length - 1] = headers;
            }
        	
            return (String) joinPoint.proceed(args);
        } catch (Exception exception) {
            String className = joinPoint.getTarget().getClass().getSimpleName();
            String methodName = joinPoint.getSignature().getName();
            log.error("Exception caught on: " + className + "." + methodName + ", Error: " + exception.getMessage());
            
            return "error";
        }
    }
    
    private void beforeLog(JoinPoint joinPoint) {
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
    private void afterLog(JoinPoint joinPoint, Object result) {
    	String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String httpMethod = request.getMethod();

        log.info("Executed [" + httpMethod + "]" + className + "." + methodName + "(), Go page: " + result);
    
    }
}
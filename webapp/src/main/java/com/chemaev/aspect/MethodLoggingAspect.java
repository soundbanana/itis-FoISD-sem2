package com.chemaev.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodLoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodLoggingAspect.class);

    @Pointcut("execution(* com.chemaev..*.*(..))")
    public void allMethods() {
    }

    @Before("allMethods()")
    public void logMethodEntry(JoinPoint joinPoint) {
        LOGGER.info("Entering method: {}", joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "allMethods()", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        LOGGER.info("Exiting method: {}", joinPoint.getSignature().toShortString());
    }

    @AfterThrowing(pointcut = "allMethods()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        LOGGER.error("Exception in method: {}", joinPoint.getSignature().toShortString());
        LOGGER.error("Exception message: {}", exception.getMessage());
    }
}


package com.chemaev.acpect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {

    public static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(Loggable)")
    public void logExecutionTime() {

    }

    @Around("logExecutionTime()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        String className = methodSignature.getDeclaringType().getName();
        String methodName = methodSignature.getName();

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();

        LOGGER.info("Execution time of {}.{} = {} ms", className, methodName, stopWatch.getTotalTimeMillis());

        return result;
    }
}
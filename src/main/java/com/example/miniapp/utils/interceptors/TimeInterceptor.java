package com.example.miniapp.utils.interceptors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ConditionalOnProperty(name = "time.tracking.enabled", havingValue = "true")
public class TimeInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(TimeInterceptor.class);

    @Around("@annotation(com.example.miniapp.interfaces.MeasureExecution)")
    public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        long startTime = System.currentTimeMillis();
        logger.info("Execution STARTED - {}.{}", className, methodName);

        try {
            return joinPoint.proceed();
        } finally {
            long executionTime = System.currentTimeMillis() - startTime;
            logger.info("Execution COMPLETED - {}.{} | Time: {} ms",
                    className, methodName, executionTime);
        }
    }
}

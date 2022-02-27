package com.peak.annotationtutorial.aoplog;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import lombok.SneakyThrows;


@Aspect
@Component
@Slf4j
@Conditional(RestLoggingCondition.class)
public class RestLogger {

    private static final String POINTCUT = "within(com.peak.annotationtutorial.controller.*)";

    @Around(POINTCUT)
    @SneakyThrows
    public Object logArroundExec(ProceedingJoinPoint pjp) {
        List<String> list = LogUtility.constructLogMsg(pjp) ;
        log.info("rest before {}", list.get(0), list.get(1));
        long start = System.currentTimeMillis();
        var proceed = pjp.proceed();
        long end = System.currentTimeMillis();
        log.info("rest after {} with ({}) result: {}", list.get(0), (end-start), proceed.toString());
        return proceed;
    }

    @AfterThrowing(pointcut = POINTCUT, throwing = "e")
    public void logAfterException(JoinPoint jp, Exception e) {
        List<String> list = LogUtility.constructLogMsg(jp) ;
        log.error("rest Exception method {} with {} with ex: {}", list.get(0), list.get(1),  e.toString());
    }

    /*
    private String constructLogMsg(JoinPoint jp) {
        var args = Arrays.asList(jp.getArgs()).stream().map(String::valueOf).collect(Collectors.joining(",", "[", "]"));
        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        var sb = new StringBuilder("@");
        sb.append(method.getName());
        sb.append(":");
        sb.append(args);
        return sb.toString();
    }

     */
}
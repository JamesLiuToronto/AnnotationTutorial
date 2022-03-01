package com.peak.annotationtutorial.aoplog;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.List;


@Aspect
@Component
@Slf4j
@Conditional(MethodLoggingCondition.class)
public class MethodLogger {

    @Around("@annotation(LogMethodData)")
    @SneakyThrows
    public Object logArroundExec(ProceedingJoinPoint pjp) {
        List<String> list = LogUtility.constructLogMsg(pjp) ;
        log.info("method {} request Parameter {}", list.get(0), list.get(1));
        //log.info("method before {}", constructLogMsg(pjp));
        long start = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        long end = System.currentTimeMillis();
        log.info("method {} response ({} ms) with result: {}", list.get(0), (end-start),  proceed.toString());
        return proceed;
    }

    /*
    @AfterThrowing
    public void logAfterException(JoinPoint jp, Exception e) {
        log.error("Method Exception during: {} with ex: {}", constructLogMsg(jp),  e.toString());
    }

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
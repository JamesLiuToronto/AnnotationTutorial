package com.peak.annotationtutorial.aoplog;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MethodLoggingCondition implements Condition {


    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String logEnabled = context.getEnvironment().getProperty("application.method.logging.enable");
        return Boolean.valueOf(logEnabled);
    }
}
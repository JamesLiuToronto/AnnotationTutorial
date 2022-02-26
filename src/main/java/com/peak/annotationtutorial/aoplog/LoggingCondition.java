package com.peak.annotationtutorial.aoplog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class  LoggingCondition implements Condition {

    @Value("${application.logging.enable}")
    private String logEnabled;

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return Boolean.valueOf(logEnabled);
    }
}
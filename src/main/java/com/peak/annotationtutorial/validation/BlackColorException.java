package com.peak.annotationtutorial.validation;

import org.springframework.validation.BindException;

public class BlackColorException extends RuntimeException {

    public BlackColorException(String errorCode) {
        super(errorCode);
    }
}

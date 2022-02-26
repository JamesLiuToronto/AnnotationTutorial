package com.peak.annotationtutorial.authorize;

public class AuthorizeException extends RuntimeException {

    public AuthorizeException(String errorCode) {
        super(errorCode);
    }
}

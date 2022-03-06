package com.peak.annotationtutorial.concurrent;

import org.springframework.stereotype.Component;

import static java.lang.Thread.sleep;

@Component
public class Calculation {

    public int add(int a, int b) throws InterruptedException {
        sleep(2000) ;
        return a + b ;

    }

    public int minus(int a, int b) throws InterruptedException {
        sleep(3000) ;
        return a - b ;
    }
}

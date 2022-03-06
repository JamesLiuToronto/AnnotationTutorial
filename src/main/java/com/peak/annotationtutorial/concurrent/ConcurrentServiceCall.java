package com.peak.annotationtutorial.concurrent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ConcurrentServiceCall {
    @Autowired
    Calculation calculation ;

    @Async
    public CompletableFuture<Integer> callAddService(int a, int b) throws InterruptedException {
        //String localSlowServiceEndpoint = "http://localhost:9000/slow";
        //JsonNode responseObj = restTemplate.getForObject(localSlowServiceEndpoint, JsonNode.class);
        //return CompletableFuture.completedFuture(responseObj);
        return CompletableFuture.completedFuture(calculation.add(a,b));
    }

    @Async
    public CompletableFuture<Integer> callMinusService(int a, int b) throws InterruptedException {
        //String localSlowServiceEndpoint = "http://localhost:9000/slow";
        //JsonNode responseObj = restTemplate.getForObject(localSlowServiceEndpoint, JsonNode.class);
        //return CompletableFuture.completedFuture(responseObj);
        return CompletableFuture.completedFuture(calculation.minus(a,b));
    }
}

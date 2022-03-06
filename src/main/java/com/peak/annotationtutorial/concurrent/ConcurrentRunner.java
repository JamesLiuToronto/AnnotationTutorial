package com.peak.annotationtutorial.concurrent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class ConcurrentRunner {

    @Autowired
    ConcurrentServiceCall concurrentServiceCall ;

    public Integer calculate()  {
        Instant start = Instant.now();
        List<CompletableFuture<Integer>> allFutures = new ArrayList<>();
        int total = 0;
        try {
            allFutures.add(concurrentServiceCall.callAddService(1,2));
            allFutures.add(concurrentServiceCall.callAddService(3,4));
            allFutures.add(concurrentServiceCall.callAddService(5,6));
            allFutures.add(concurrentServiceCall.callAddService(7,8));
            allFutures.add(concurrentServiceCall.callAddService(9,10));
            CompletableFuture.allOf(allFutures.toArray(new CompletableFuture[0]));

            for (CompletableFuture<Integer> rec:allFutures) {
                total = total + rec.get();

                //System.out.println("response: " + allFutures.get(i).get().toString());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return -1;
        }


        System.out.println("Total = " + total);
        System.out.println("Total time: " + Duration.between(start, Instant.now()).getSeconds());
        return total ;
    }
}

package com.peak.annotationtutorial.heap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class MemoryStatusController {

    @GetMapping("memory-status")
    public MemoryStats getMemoryStatistics() {
        MemoryStats stats = new MemoryStats();

        stats.setHeapSize(convertToMb(Runtime.getRuntime().totalMemory()));
        stats.setHeapMaxSize(convertToMb(Runtime.getRuntime().maxMemory()));
        stats.setHeapFreeSize(convertToMb(Runtime.getRuntime().freeMemory()));
        return stats;
    }

    private long convertToMb(long size){
        BigDecimal mb = new BigDecimal("9.537E-7") ;
        mb = mb.multiply(BigDecimal.valueOf(size)) ;
        return mb.longValue() ;
    }

    @GetMapping("http-size")
    public boolean testMaxHTTPHeaderSize(@RequestHeader(value = "token") String token) {
        return true;
    }
}

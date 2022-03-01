package com.peak.annotationtutorial.heap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoryStats {
    private long heapSize;
    private long heapMaxSize;
    private long heapFreeSize;
}

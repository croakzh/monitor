package com.croakzh.core.entity;

import lombok.Data;

/**
 * @author croakzh
 */
@Data
public class MemoryInfo {

    private Long memTotal = 0L;

    private Long memFree = 0L;

    private Long memAvailable = 0L;

    private Long activeFile = 0L;

    private Long inactiveFile = 0L;

    private Long sReclaimable = 0L;

    private Long swapTotal = 0L;

    private Long swapFree = 0L;

    private Long swapUsed = 0L;

    private Double usedPercent = 0.0D;
    private Double freePercent = 0.0D;
}

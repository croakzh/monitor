package com.croakzh.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Cpu信息
 *
 * @author croakzh
 */
@Data
public class CpuInfo {

    /**
     * Cpu总核数
     */
    private Integer totalCores = 0;
    /**
     * Cpu总套接字量
     */
    private Integer totalSockets = 0;
    /**
     * 每个套接字的核心线程数
     */
    private Integer coresPerSocket = 0;

    private List<CpuDetailInfo> perCpus = new ArrayList<>();

    /**
     * 增加单块CPU详情
     *
     * @param cpuDetailInfo CPU详情
     */
    public void addCpu(CpuDetailInfo cpuDetailInfo) {
        perCpus.add(cpuDetailInfo);
    }

    /**
     * Cpu相关详情
     */
    @AllArgsConstructor
    @Data
    public static class CpuDetailInfo {

        public CpuDetailInfo() {
            super();
        }

        /**
         * Cpu生产商
         */
        private String vendor = "";
        /**
         * Cpu类别
         */
        private String model = "";
        /**
         * Cpu主频
         */
        private Integer mhz = 0;
        /**
         * Cpu缓存数量
         */
        private Long cacheSize = 0L;
    }

}

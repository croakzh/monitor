package com.croakzh.service;

import com.croakzh.core.entity.CpuInfo;
import com.croakzh.core.entity.IOStatInfo;
import com.croakzh.core.entity.MemoryInfo;
import com.croakzh.core.entity.vo.ServerVo;

/**
 * 具体的系统服务
 *
 * @author croakzh
 */
public interface ISystemService {

    /**
     * 获取具体的cpu属性
     *
     * @param serverVo 服务器信息
     * @return cpu详情
     * @throws Exception {@link Exception} 异常
     */
    CpuInfo getCpuInfo(ServerVo serverVo) throws Exception;

    /**
     * 获取具体的内存详情
     * @param serverVo 服务器信息
     * @return 内存详情
     * @throws Exception {@link Exception} 异常
     */
    MemoryInfo getMemInfo(ServerVo serverVo) throws Exception;

    /**
     * 获取IO盘的信息
     * @param serverVo 服务器信息
     * @return IO盘信息
     * @throws Exception {@link Exception} 异常
     */
    IOStatInfo getIOInfo(ServerVo serverVo) throws Exception;




}

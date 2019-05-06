package com.croakzh.service.impl;

import com.croakzh.core.entity.CpuInfo;
import com.croakzh.core.entity.IOStatInfo;
import com.croakzh.core.entity.MemoryInfo;
import com.croakzh.core.entity.vo.ServerVo;
import com.croakzh.service.AbstractSystemService;
import com.croakzh.service.Validator;

/**
 * @author croakzh
 */
public class WindowsServiceImpl extends AbstractSystemService {


    @Override
    public CpuInfo getCpuInfo(ServerVo serverVo) throws Exception {
        return null;
    }

    @Override
    public MemoryInfo getMemInfo(ServerVo serverVo) throws Exception {
        return null;
    }

    @Override
    public IOStatInfo getIOInfo(ServerVo serverVo) throws Exception {
        return null;
    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public String getSystemServiceChannel() {
        return null;
    }
}

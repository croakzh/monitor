package com.croakzh.service;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author croakzh
 */
@Slf4j
public abstract class AbstractSystemService implements ISystemService {

    /**
     * 维系不同系统服务接口
     */
    private Map<String, AbstractSystemService> systemsMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        systemsMap.put(getSystemServiceChannel(), this);
    }

    /**
     * 获得具体的校验器
     *
     * @return {@link Validator} 具体的校验器
     */
    public abstract Validator getValidator();

    /**
     * 获取获取系统信息通道
     *
     * @return 具体类型
     */
    public abstract String getSystemServiceChannel();

    /**
     * 把kB大小转成byte大小，并只取数量级
     *
     * @param kbLong 10000 kB
     * @return 10240000
     */
    protected Long parseKbLong(String kbLong) {
        return Long.parseLong(kbLong.replace("kB", "").trim()) * 1024;
    }

}

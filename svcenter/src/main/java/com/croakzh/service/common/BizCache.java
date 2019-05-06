package com.croakzh.service.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * BizCache 公共操作类
 *
 * @author croakzh
 */
public class BizCache {

    private static BizCache instance;

    public static synchronized BizCache getInstance() {
        if (instance == null) {
            instance = new BizCache();
        }
        return instance;
    }

    /**
     * 获取符合SQL格式的当前时间，YYYY-MM-DD HH24:mm:ss格式
     *
     * @return 格式化的当前时间
     */
    public String getNow() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

}

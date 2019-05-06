package com.croakzh.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统类型枚举
 *
 * @author croakzh
 */
@Getter
@AllArgsConstructor
public enum SystemTypeEnum {

    /**
     * linux 服务器
     */
    Linux("Linux", "linux 服务器"),
    /**
     * window 服务器
     */
    Windows("Windows", "Windows 服务器");

    private String code;

    private String systemDesc;


}

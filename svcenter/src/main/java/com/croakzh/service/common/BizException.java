package com.croakzh.service.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义异常
 *
 * @author croakzh
 */
@Data
@AllArgsConstructor
public class BizException extends RuntimeException {

    /**
     * serialVersionUID 持久化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer errorcode = 0;
    /**
     * 错误描述
     */
    private String description = "";


    public BizException(Integer errorcode) {
        this.errorcode = errorcode;
    }

}

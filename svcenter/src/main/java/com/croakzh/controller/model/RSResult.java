package com.croakzh.controller.model;

import lombok.Data;

/**
 * {@code RSResult}统一定义的返回
 *
 * @author croakzh
 */
@Data
public class RSResult {

    /**
     * 返回客户端的错误代码，0表示没有错误。不等于0时，message内填充了错误文本提示
     */
    private int result;

    /**
     * 返回客户端的列表元素个数，在查询list时这个值才有效
     */
    private int total;

    /**
     * 返回客户端的文本提示
     */
    private String message;

    /**
     * 返回客户端的数据对象
     */
    private Object data;

    public RSResult() {
        super();
    }

    public RSResult(int result) {
        super();
        this.result = result;
    }

    public RSResult(int result, String message) {
        super();
        this.result = result;
        this.message = message;
    }

    public void setResult(int errorCode, String message, String defaultMessage) {
        try {
            this.result = errorCode;
            this.message = "".equals(message) ? defaultMessage : message;
        } catch (Exception ex) {
            this.result = -1;
            this.message = "".equals(message) ? defaultMessage : message;
        }
    }

    public void setResult(Integer errorcode, String message) {
        this.result = errorcode;
        this.message = message;
    }

}

package com.croakzh.webfront.po;

import com.croakzh.webfront.po.base.PageBean;
import lombok.Data;

import java.io.Serializable;

/**
 * 服务器性能记录表
 *
 * @author croakzh
 * @since 2018-12-05
 */
@Data
public class NaturelogPo extends PageBean implements Serializable {

    /**
     * serialVersionUID 持久化ID
     */
    private static final Long serialVersionUID = 1L;

    /**
     * 服务器性能记录
     */
    private Integer id;

    /**
     * 性能详情
     */
    private String detail;

    /**
     * 服务器id
     */
    private Integer serverid;

    /**
     * 记录时间
     */
    private String addtime;

}

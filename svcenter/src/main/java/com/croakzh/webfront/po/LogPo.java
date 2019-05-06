package com.croakzh.webfront.po;

import com.croakzh.webfront.po.base.PageBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 应用日志监控
 *
 * @author croakzh
 */
@Data
@ToString
@AllArgsConstructor
public class LogPo extends PageBean implements Serializable {

    /**
     * serialVersionUID 持久化ID
     */
    private static final Long serialVersionUID = 1L;

    /**
     * 日志存储id,主键
     */
    private Integer id;

    /**
     * 日志详情
     */
    private String detail;

    /**
     * 服务器id,不显示;
     */
    private Integer serverid;

    /**
     * 应用id,不显示;
     */
    private Integer applicationid;

    /**
     * logtime 时间串 [18:15:32:546]，用于判重，不予展示
     */
    private String logtime;

    /**
     * 服务器名
     */
    private String hostname;

    /**
     * 服务器ip
     */
    private String host;

    /**
     * 应用名称
     */
    private String applicationname;

    public LogPo() {
        super();
    }

    public LogPo(Integer serverid, Integer applicationid) {
        super();
        this.serverid = serverid;
        this.applicationid = applicationid;
    }

}

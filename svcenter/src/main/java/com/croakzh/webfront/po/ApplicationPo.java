package com.croakzh.webfront.po;

import com.croakzh.webfront.po.base.PageBean;
import lombok.Data;

import java.io.Serializable;

/**
 * 服务器应用配置
 *
 * @author croakzh
 */
@Data
public class ApplicationPo extends PageBean implements Serializable {

    /**
     * serialVersionUID 持久化ID
     */
    private static final Long serialVersionUID = 1L;

    /**
     * 应用id,主键
     */
    private Integer applicationid;

    /**
     * 服务器id,不显示;
     */
    private Integer serverid;

    /**
     * 应用名称,必填;最大长度:32;
     */
    private String applicationname;

    /**
     * 应用状态
     */
    private Byte appstatus;

    /**
     * 应用部署路径,必填;最大长度:64;
     */
    private String developpath;

    /**
     * 描述,必填;最大长度:255;
     */
    private String description;

    /**
     * 新增时间
     */
    private String addtime;

    /**
     * 修改时间
     */
    private String updatetime;

    /**
     * 服务器名称
     */
    private String hostname;

    /**
     * 服务器ip
     */
    private String host;

    // 文件属性
    private String uid;
    private String name;

}

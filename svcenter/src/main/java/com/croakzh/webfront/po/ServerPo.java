package com.croakzh.webfront.po;

import com.croakzh.webfront.po.base.PageBean;
import lombok.Data;

import java.io.Serializable;

/**
 * 服务器监控
 *
 * @author croakzh
 */
@Data
public class ServerPo extends PageBean implements Serializable {

    /**
     * serialVersionUID 持久化ID
     */
    private static final Long serialVersionUID = 1L;

    /**
     * 服务器id,主键
     */
    private Integer serverid;

    /**
     * 机器组id,必填;控件:[下拉框,"group"];检索条件;
     */
    private Integer groupid;

    /**
     * 服务器ip,必填
     */
    private String host;

    /**
     * 服务器名称,必填;最大长度:64;
     */
    private String hostname;

    /**
     * 描述,控件:[文本框,""];最大长度:255;
     */
    private String description;

    /**
     * 连接端口,必填
     */
    private String sshport;

    /**
     * 连接用户名,必填;最大长度:64;
     */
    private String sshname;

    /**
     * 连接密码,必填;最大长度:64;
     */
    private String sshpwd;

    /**
     * 登录状态,不显示;控件:[下拉框,"在线|离线"];检索条件;
     */
    private Integer status;

    /**
     * 自定义脚本集合
     */
    private String shells;

    /**
     * 新增时间
     */
    private String addtime;

    /**
     * 修改时间
     */
    private String updatetime;

    /**
     * 权限组名;不显示
     */
    private String groupname;

}

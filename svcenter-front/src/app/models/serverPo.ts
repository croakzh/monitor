import {PageBean} from "./pageBean";

/**
 * 服务器监控
 *
 * @author zhangshengze
 */
export class ServerPo extends PageBean {
    /*主键*/
    public ztIdField = "t_serverid";

    // 服务器id,主键
    public serverid: number;
    // 机器组id,必填;不显示
    public groupid: number;
    // 服务器ip,必填
    public host: string;
    // 服务器名称,必填;最大长度:64;
    public hostname: string;
    // 描述,控件:[文本框,""];最大长度:255;
    public description: string;
    // 连接端口,必填
    public sshport: string;
    // 连接用户名,必填;最大长度:64;
    public sshname: string;
    // 连接密码,必填;最大长度:64;
    public sshpwd: string;
    // 登录状态
    public status: number;
    // 自定义脚本集合
    public shells: string;
    // 新增时间
    public addtime: string;
    // 修改时间
    public updatetime: string;
    // 权限组名
    public groupname: string;

    // 用作页面展示 't_' 开头
    public t_serverid: number;
    public t_groupid: number;
    public t_host: string;
    public t_hostname: string;
    public t_description: string;
    public t_sshport: string;
    public t_sshname: string;
    public t_sshpwd: string;
    public t_status: number;
    public t_shells: string;
    public t_addtime: string;
    public t_updatetime: string;
    public t_groupname: string;

}

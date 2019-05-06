import {PageBean} from "./pageBean";

/**
 * 应用日志监控
 *
 * @author zhangshengze
 */
export class LogPo extends PageBean {
    /*主键*/
    public ztIdField = "t_id";

    // 日志存储id,主键
    public id: number;
    // 日志详情
    public detail: string;
    // 服务器id,不显示;
    public serverid: number;
    // 应用id,不显示;
    public applicationid: number;
    // 服务器名
    public hostname: string;
    // 服务器ip
    public host: string;
    // 应用名称
    public applicationname: string;

    // 用作页面展示 't_' 开头
    public t_id: number;
    public t_detail: string;
    public t_serverid: number;
    public t_applicationid: number;
    public t_hostname: string;
    public t_host: string;
    public t_applicationname: string;

}
import {PageBean} from "./pageBean";

/**
 * 服务器应用配置
 *
 * @author zhangshengze
 */
export class ApplicationPo extends PageBean {
    /*主键*/
    public ztIdField = "t_applicationid";

    // 应用id,主键
    public applicationid: number;
    // 服务器id,不显示;
    public serverid: number;
    // 应用名称,必填;最大长度:32;
    public applicationname: string;
    // 应用部署路径,必填;最大长度:64;
    public developpath: string;
    // 描述,必填;最大长度:255;
    public description: string;
    // 应用状态
    public appstatus: number;
    // 新增时间
    public addtime: string;
    // 修改时间
    public updatetime: string;
    // 服务器名称
    public hostname: string;
    // 服务器ip
    public host: string;

    public name: string;
    public uid: string;

    // 用作页面展示 't_' 开头
    public t_applicationid: number;
    public t_serverid: number;
    public t_applicationname: string;
    public t_developpath: string;
    public t_description: string;
    public t_status: number;
    public t_addtime: string;
    public t_updatetime: string;
    public t_hostname: string;
    public t_host: string;

}

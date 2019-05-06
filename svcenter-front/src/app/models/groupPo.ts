import {PageBean} from "./pageBean";

/**
 * 服务器组配置
 *
 * @author zhangshengze
 */
export class GroupPo extends PageBean {
    /*主键*/
    public ztIdField = "t_groupid";

    // 权限组id,主键
    public groupid: number;
    // 组名,必填;最大长度:64;
    public groupname: string;
    // 描述,控件:[文本框,""];
    public description: string;
    // 新增时间
    public addtime: string;
    // 修改时间
    public updatetime: string;

    // 用作页面展示 't_' 开头
    public t_groupid: number;
    public t_groupname: string;
    public t_description: string;
    public t_addtime: string;
    public t_updatetime: string;

}
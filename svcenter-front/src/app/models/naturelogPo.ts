import {PageBean} from "./pageBean";

/**
 * 服务器性能记录表
 *
 * @author zhangshengze
 */
export class NaturelogPo extends PageBean {

    // 服务器性能记录
    public id: number;
    // 性能详情
    public detail: string;
    // 服务器id
    public serverid: number;
    // 记录时间
    public addtime: string;

    // 用作页面展示 't_' 开头
    public t_id: number;
    public t_detail: string;
    public t_serverid: number;
    public t_addtime: string;

}
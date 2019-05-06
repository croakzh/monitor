import {BaseBean} from "./baseBean";

export class PageBean extends BaseBean {
    /**
     * 总记录数
     */
    public total: number;
    /**
     * 页码
     */
    public curpage: number;
    /**
     * 每页笔数
     */
    public pagesize: number;

    constructor() {
        super();
    }
}

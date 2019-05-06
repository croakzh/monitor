import {PageBean} from "../../models/pageBean";

export class TableData<T> extends PageBean {
    // 加载中
    public loading: boolean;

    // 显示详情
    public showDetail: boolean;

    // 分页数据
    public data: T[];

    // 构造函数
    constructor() {
        super();
        this.curpage = 1;
        this.pagesize = 10;
    }
}

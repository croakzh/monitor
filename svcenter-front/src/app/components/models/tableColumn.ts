export class TableColumn {
    // 标题
    public title: string;

    // 字段
    public field: string;

    // 可见性
    public visible: boolean = false;

    public isSort: boolean = false;

    // 附加属性
    public data: any;

    // 回调函数
    public render: any;
}

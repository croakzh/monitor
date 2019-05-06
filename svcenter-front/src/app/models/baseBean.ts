export class BaseBean {
    // 主键
    public ztIdField = null;
    // 勾选
    public ztChecked: boolean = false;
    /* 排序方式 */
    public sortord: string;
    /* 模糊条件 */
    public term: string; /* 模糊条件 */
}

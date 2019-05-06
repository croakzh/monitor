export class rSResult<T> {
    /**
     * code: 状态码, 0成功, 1一般业务异常, -1其他异常, -2授权异常, 令牌错误或令牌过期
     */
    public result: number;

    // 列表總數
    public total: number;
    /**
     * message: 错误消息
     */
    public message: string;

    /**
     * data: 数据
     */
    public data: T | T[];
}

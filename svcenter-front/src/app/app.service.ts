import { EventEmitter, Injectable } from "@angular/core";

/**
 * app服务
 */
@Injectable()
export class AppService {
    // 标题
    public titleEventEmitter: EventEmitter<string>;

    constructor() {
        this.titleEventEmitter = new EventEmitter();
    }
}

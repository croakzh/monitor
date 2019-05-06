import {Injectable} from "@angular/core";
import {BaseService} from "./base.service";
import {LogPo} from "../models/logPo";
import {HttpClientService} from "./httpClient.service";
import {Observable} from "rxjs";
import {rSResult} from "../models/rSResult";
/**
 * 表 Log 服务层接口
 *
 * @author zhangshengze
 */
@Injectable()
export class LogService extends BaseService<LogPo> {

    constructor(protected httpClientService: HttpClientService) {
        super();
    }
    public selectByPage(params: any): Observable<rSResult<LogPo>> {
        const url = "/log/list";
        return this.httpClientService.post(url, params);
    }

    public deleteById(params: any): Observable<rSResult<LogPo>> {
        const url = "/log/delete";
        return this.httpClientService.post(url, params);
    }

    public addOrUpdate(params: any, add: boolean): Observable<rSResult<LogPo>> {
        let url = "/log/update";
        if (add) {
            url = "/log/new";
        }
        return this.httpClientService.post(url, params);
    }

    public selectById(params: any): Observable<rSResult<LogPo>> {
        const url = "/log/detail";
        return this.httpClientService.post(url, params);
    }

}
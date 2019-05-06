import {Injectable} from "@angular/core";
import {BaseService} from "./base.service";
import {ServerPo} from "../models/serverPo";
import {HttpClientService} from "./httpClient.service";
import {Observable} from "rxjs";
import {rSResult} from "../models/rSResult";

/**
 * 表Server服务层接口
 *
 * @author zhangshengze
 */
@Injectable()
export class ServerService extends BaseService<ServerPo> {

    constructor(protected httpClientService: HttpClientService) {
        super();
    }

    public selectByPage(params: any): Observable<rSResult<ServerPo>> {
        const url = "/server/list";
        return this.httpClientService.post(url, params);
    }

    public deleteById(params: any): Observable<rSResult<ServerPo>> {
        const url = "/server/delete";
        return this.httpClientService.post(url, params);
    }

    public addOrUpdate(params: any, add: boolean): Observable<rSResult<ServerPo>> {
        let url = "/server/update";
        if (add) {
            url = "/server/new";
        }
        return this.httpClientService.post(url, params);
    }

    public selectById(params: any): Observable<rSResult<ServerPo>> {
        const url = "/server/detail";
        return this.httpClientService.post(url, params);
    }

    public testConnect(params: any): Observable<rSResult<Object>> {
        const url = "/connect/test";
        return this.httpClientService.post(url, params);
    }

    public refresh(params: any): Observable<rSResult<Object>> {
        const url = "/server/refresh";
        return this.httpClientService.post(url, params);
    }

}

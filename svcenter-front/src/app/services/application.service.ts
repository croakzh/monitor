import {Injectable} from "@angular/core";
import {BaseService} from "./base.service";
import {ApplicationPo} from "../models/applicationPo";
import {HttpClientService} from "./httpClient.service";
import {Observable} from "rxjs";
import {rSResult} from "../models/rSResult";

/**
 * 表 Application 服务层接口
 *
 * @author zhangshengze
 */
@Injectable()
export class ApplicationService extends BaseService<ApplicationPo> {

    constructor(protected httpClientService: HttpClientService) {
        super();
    }

    public selectByPage(params: any): Observable<rSResult<ApplicationPo>> {
        const url = "/application/list";
        return this.httpClientService.post(url, params);
    }

    public deleteById(params: any): Observable<rSResult<ApplicationPo>> {
        const url = "/application/delete";
        return this.httpClientService.post(url, params);
    }

    public addOrUpdate(params: any, add: boolean): Observable<rSResult<ApplicationPo>> {
        let url = "/application/update";
        if (add) {
            url = "/application/new";
        }
        return this.httpClientService.post(url, params);
    }

    public setupApplication(params: any, start: boolean): Observable<rSResult<ApplicationPo>> {
        let url = "/application/stop";
        if (start) {
            url = "/application/start";
        }
        return this.httpClientService.post(url, params);
    }

    public selectById(params: any): Observable<rSResult<ApplicationPo>> {
        const url = "/application/detail";
        return this.httpClientService.post(url, params);
    }

    public testApplication(params: any): Observable<rSResult<Object>> {
        const url = "/application/test";
        return this.httpClientService.post(url, params);
    }

    public releaseApplication(params: any): Observable<rSResult<Object>> {
        const url = "/application/release";
        return this.httpClientService.post(url, params);
    }

}

import {Injectable} from "@angular/core";
import {BaseService} from "./base.service";
import {NaturelogPo} from "../models/naturelogPo";
import {HttpClientService} from "./httpClient.service";
import {Observable} from "rxjs";
import {rSResult} from "../models/rSResult";
/**
 * 表 Naturelog 服务层接口
 *
 * @author zhangshengze
 */
@Injectable()
export class NaturelogService extends BaseService<NaturelogPo> {

    constructor(protected httpClientService: HttpClientService) {
        super();
    }
    public selectByPage(params: any): Observable<rSResult<NaturelogPo>> {
        const url = "/naturelog/list";
        return this.httpClientService.post(url, params);
    }

    public deleteById(params: any): Observable<rSResult<NaturelogPo>> {
        const url = "/naturelog/delete";
        return this.httpClientService.post(url, params);
    }

    public addOrUpdate(params: any, add: boolean): Observable<rSResult<NaturelogPo>> {
        let url = "/naturelog/update";
        if (add) {
            url = "/naturelog/new";
        }
        return this.httpClientService.post(url, params);
    }

    public selectById(params: any): Observable<rSResult<any>> {
        const url = "/naturelog/detail";
        return this.httpClientService.post(url, params);
    }

}

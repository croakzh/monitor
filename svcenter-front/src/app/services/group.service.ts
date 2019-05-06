import {Injectable} from "@angular/core";
import {BaseService} from "./base.service";
import {GroupPo} from "../models/groupPo";
import {HttpClientService} from "./httpClient.service";
import {Observable} from "rxjs";
import {rSResult} from "../models/rSResult";
/**
 * 表 Group 服务层接口
 *
 * @author zhangshengze
 */
@Injectable()
export class GroupService extends BaseService<GroupPo> {

    constructor(protected httpClientService: HttpClientService) {
        super();
    }
    public selectByPage(params: any): Observable<rSResult<GroupPo>> {
        const url = "/group/list";
        return this.httpClientService.post(url, params);
    }

    public deleteById(params: any): Observable<rSResult<GroupPo>> {
        const url = "/group/delete";
        return this.httpClientService.post(url, params);
    }

    public addOrUpdate(params: any, add: boolean): Observable<rSResult<GroupPo>> {
        let url = "/group/update";
        if (add) {
            url = "/group/new";
        }
        return this.httpClientService.post(url, params);
    }

    public selectById(params: any): Observable<rSResult<GroupPo>> {
        const url = "/group/detail";
        return this.httpClientService.post(url, params);
    }

}
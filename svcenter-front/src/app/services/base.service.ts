import { Observable } from "rxjs/Observable";
import { rSResult } from "../models/rSResult";
import { Injectable } from "@angular/core";

@Injectable()
export class BaseService<T> {
    // 分页查询
    public selectByPage(params: any): Observable<rSResult<T>> {
        throw new Error("selectByPage not implemented yet");
    }

    // 查询详情
    public selectById(params: any): Observable<rSResult<T>> {
        throw new Error("selectById not implemented yet");
    }

    // 新增或更新
    public addOrUpdate(params: any, add: boolean): Observable<rSResult<T>> {
        throw new Error("addOrUpdate not implemented yet");
    }

    // 删除
    public deleteById(params: any): Observable<rSResult<T>> {
        throw new Error("deleteById not implemented yet");
    }

    // 批量删除
    public deleteByIds(ids): Observable<rSResult<string>> {
        throw new Error("deleteByIds not implemented yet");
    }

}

import {HttpClient} from "@angular/common/http";
// import { Http } from "@angular/http";
import {Injectable} from "@angular/core";

import {Observable} from "rxjs/Observable";
import {NzMessageService} from "ng-zorro-antd";
import {Router} from "@angular/router";
import {environment} from "../../environments/environment.prod";

/**
 * httpclient服务
 */
@Injectable()
export class HttpClientService {

    constructor(
        private router: Router,
        private httpClient: HttpClient,
        private messageService: NzMessageService,
    ) {

    }

    /**
     * request
     * @return any
     */
    public request(method: string, url: string, params: { [param: string]: string | string[] }): Observable<any> {
        let myUrl = environment.baseApiUrl + url;
        if (url.startsWith("http://") || url.startsWith("https://")) {
            myUrl = url;
        }
        this.commonParamProcess(params);
        const options = {params};
        return this.httpClient.request(method, myUrl, options);
    }

    public delete(url: string, params: { [param: string]: string | string[] }): Observable<any> {
        let myUrl = environment.baseApiUrl + url;
        if (url.startsWith("http://") || url.startsWith("https://")) {
            myUrl = url;
        }
        this.commonParamProcess(params);
        const options = {params};
        console.log(options);
        return this.commonProcess(this.httpClient.delete(myUrl, options));
    }

    public get(url: string, params: { [param: string]: string | string[] }): Observable<any> {
        let myUrl = environment.baseApiUrl + url;
        if (url.startsWith("http://") || url.startsWith("https://")) {
            myUrl = url;
        }
        this.commonParamProcess(params);
        const options = {params};
        return this.commonProcess(this.httpClient.get(myUrl, options));
    }

    /**
     * head
     * @param url
     * @param options
     */
    public head(url: string, params: { [param: string]: string | string[] }): Observable<any> {
        let myUrl = environment.baseApiUrl + url;
        if (url.startsWith("http://") || url.startsWith("https://")) {
            myUrl = url;
        }
        this.commonParamProcess(params);
        const options = {params};
        return this.commonProcess(this.httpClient.head(myUrl, options));
    }

    public options(url: string, params: { [param: string]: string | string[] }): Observable<any> {
        let myUrl = environment.baseApiUrl + url;
        if (url.startsWith("http://") || url.startsWith("https://")) {
            myUrl = url;
        }
        this.commonParamProcess(params);
        const options = {params};
        return this.commonProcess(this.httpClient.options(myUrl, options));
    }

    public patch(url: string, body: any | null, params?: { [param: string]: string | string[] }): Observable<any> {
        let myUrl = environment.baseApiUrl + url;
        if (url.startsWith("http://") || url.startsWith("https://")) {
            myUrl = url;
        }
        this.commonParamProcess(params);
        const options = {params};
        return this.commonProcess(this.httpClient.patch(myUrl, body, options));
    }

    public post(url: string, body: any | null, params?: { [param: string]: string | string[] }): Observable<any> {
        let myUrl = environment.baseApiUrl + url;
        if (url.startsWith("http://") || url.startsWith("https://")) {
            myUrl = url;
        }
        this.commonParamProcess(params);
        const options = {params};
        return this.commonProcess(this.httpClient.post(myUrl, body, options));
    }

    public put(url: string, body: any | null, params?: { [param: string]: string | string[] }): Observable<any> {
        let myUrl = environment.baseApiUrl + url;
        if (url.startsWith("http://") || url.startsWith("https://")) {
            myUrl = url;
        }
        this.commonParamProcess(params);
        const options = {params};
        return this.commonProcess(this.httpClient.put(myUrl, body, options));
    }

    public commonHandlerError(res) {
        this.messageService.error(res.message);
    }

    public commonParamProcess(params) {
        for (const key in params) {
            if (params[key] === null) {
                delete params[key];
            }
        }
    }

    /**
     * 公共处理
     * @param observable
     */
    public commonProcess(observable: Observable<any>): Observable<any> {
        return Observable.create((observer) => {
            observable.subscribe((res) => {

                if (environment.baseApiUrl.indexOf("192.168.1.101") > 0) {
                    observer.next(res);
                    return;
                }

                if (res.result !== 0) {
                    observer.error(res);
                    if (!res.handled) {
                        this.commonHandlerError(res);
                    }
                } else {
                    observer.next(res);
                }
            }, (err) => {
                const res = {result: -1, message: err.statusText, handled: false};
                observer.error(res);
                if (!res.handled) {
                    this.commonHandlerError(res);
                }
            }, () => {
                observer.complete();
            });
        });
    }

}

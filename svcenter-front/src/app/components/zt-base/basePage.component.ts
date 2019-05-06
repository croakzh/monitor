import {forwardRef, Injector} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {NG_VALUE_ACCESSOR} from "@angular/forms";
import {NzMessageService, NzModalService} from "ng-zorro-antd";
import {Location} from "@angular/common";
import {Utils} from "../../utils/utils";
import {ValidService} from "../../utils/valid.service";
import {environment} from "../../../environments/environment.prod";

const ZT_BASEPAGE_CONTROL_VALUE_ACCESSOR: any = {
    provide: NG_VALUE_ACCESSOR,
    useExisting: forwardRef(() => BasePageComponent),
    multi: true,
};

// 详情页面基类
export abstract class BasePageComponent {

    // 服务
    protected router: Router;
    protected modalService: NzModalService;
    protected messageService: NzMessageService;
    protected activatedRoute: ActivatedRoute;
    protected validService: ValidService;
    protected location: Location;

    // 构造函数
    constructor(
        protected injector: Injector,
    ) {
        this.onInit();

        // 服务注入
        this.router = injector.get(Router);

        this.modalService = injector.get(NzModalService);

        this.activatedRoute = injector.get(ActivatedRoute);

        this.messageService = injector.get(NzMessageService);

        this.validService = injector.get(ValidService);

        this.location = injector.get(Location);
    }

    // 初始化
    public onInit() {
        //
    }

    // 返回
    public goBack() {
        this.location.back();
    }

    // 下载
    public download(url, params) {
        const Request = Utils.urlSearch(window.location.href);
        if (Request.token != null && window.localStorage.getItem("accessToken") !== Request.token) {
            window.localStorage.setItem("accessToken", Request.token);
        }
        params["User-Token"] = window.localStorage.getItem("accessToken");
        let data = "";
        for (const i in params) {
            if (params[i] !== "" && params[i] !== null) {
                data = data + i + "=" + params[i] + "&";
            }
        }
        data = data.substring(0, data.length - 1);

        if (url.indexOf("?") > 0) {
            window.open(environment.baseApiUrl + url + "" + data);
        } else {
            window.open(environment.baseApiUrl + url + "?" + data);
        }
    }
}

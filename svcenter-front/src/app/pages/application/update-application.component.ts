import {Component, Injector} from "@angular/core";
import {BaseRecordComponent} from "../../components/zt-base/baseRecord.component";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ApplicationService} from "../../services/application.service";
import {ApplicationPo} from "../../models/applicationPo";
import {ServerService} from "../../services/server.service";
import {ServerPo} from "../../models/serverPo";

@Component({
    selector: "app-update-application",
    templateUrl: "./update-application.component.html",
    styleUrls: ["./update-application.component.scss"],
    host: {
        "[class.sd-content]": "true",
    },
})

export class UpdateApplicationComponent extends BaseRecordComponent<ApplicationPo> {

    public form: FormGroup;

    public loadingTest = false;
    public applicationStatus: number = 0;
    public servers = [];

    constructor(protected injector: Injector, private fb: FormBuilder,
                protected applicationService: ApplicationService,
                protected serverService: ServerService) {
        super(injector, ApplicationPo, applicationService);
        this.applicationService = injector.get(ApplicationService);
        this.serverService = injector.get(ServerService);
    }

    public submitForm(): void {
        for (const i in this.form.controls) {
            this.form.controls[i].markAsDirty();
            this.form.controls[i].updateValueAndValidity();
        }
        if (this.form.valid) {
            this.applicationService.addOrUpdate(this.copy4Post(this.form.value), false).subscribe((res) => {
                this.messageService.success("操作成功");
                this.router.navigate(["/list-application"]);
            });
        }
    }

    /**
     * 提交前元素内容拷贝
     */
    public copy4Post(application: ApplicationPo) {
        application.appstatus = this.applicationStatus;
        return application;
    }

    /**
     * 初始化方法
     */
    public ngOnInit(): void {
        const id = localStorage.getItem("id");
        this.form = this.fb.group({
            applicationid: ["", []],
            serverid: ["", [Validators.required]],
            applicationname: ["", [Validators.required, Validators.maxLength(32)]],
            appstatus: ["", []],
            developpath: ["", [Validators.required, Validators.maxLength(64)]],
            description: ["", [Validators.required, Validators.maxLength(255)]],
            addtime: ["", []],
            updatetime: ["", []],
            hostname: ["", []],
            host: ["", []],
        });
        this.serverService.selectByPage({curpage: 1, pagesize: 9999}).subscribe((data) => {
            (data.data as ServerPo[]).forEach((ele) => {
                this.servers.push(ele);
            })
        });
        const temp = new ApplicationPo();
        temp.applicationid = Number(id);
        this.applicationService.selectById(temp)
            .subscribe((data) => {
                this.form.setValue(this.copyContent(data.data as ApplicationPo));
            });
    }

    /**
     * 回退
     */
    public back() {
        this.router.navigate(["/list-application"]);
    }

    public copyContent(application: ApplicationPo) {
        application.applicationid = this.transContent(application.applicationid);
        application.serverid = this.transContent(application.serverid);
        application.applicationname = this.transContent(application.applicationname);
        application.developpath = this.transContent(application.developpath);
        application.description = this.transContent(application.description);
        application.addtime = this.transContent(application.addtime);
        application.updatetime = this.transContent(application.updatetime);
        application.hostname = this.transContent(application.hostname);
        application.host = this.transContent(application.host);
        return application;
    }

    public transContent(content: any) {
        if (content == null) {
            return "";
        }
        return content;
    }

    /**
     * 应用测试
     */
    public test() {
        if (this.form.value.developpath == '') {
            this.messageService.warning("应用部署路径不能为空！");
            return;
        }
        if (!new RegExp("^\\/(\\w+\\/?)+$").test(this.form.value.developpath)) {
            this.messageService.warning("应用部署路径有误！")
            return;
        }
        if (this.form.value.serverid == '') {
            this.messageService.warning("请选择对应的服务器！");
            return;
        }
        this.loadingTest = true;
        this.applicationService.testApplication(this.form.value).subscribe((res) => {
            if (res.result == 0) {
                this.loadingTest = false;
                this.applicationStatus = Number(JSON.stringify(res.data));
                this.messageService.success(res.message);
            }
        }, err => {
            console.log(err);
            this.loadingTest = false;
        })

    }
}

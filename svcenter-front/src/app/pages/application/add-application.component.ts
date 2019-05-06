import {Component, Injector} from "@angular/core";
import {BaseRecordComponent} from "../../components/zt-base/baseRecord.component";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ApplicationService} from "../../services/application.service";
import {ApplicationPo} from "../../models/applicationPo";
import {ServerService} from "../../services/server.service";
import {ServerPo} from "../../models/serverPo";

@Component({
    selector: "app-add-application",
    templateUrl: "./add-application.component.html",
    styleUrls: ["./add-application.component.scss"],
    host: {
        "[class.sd-content]": "true",
    },
})
export class AddApplicationComponent extends BaseRecordComponent<ApplicationPo> {

    public form: FormGroup;

    public servers = [];
    public applicationStatus: number = 0;
    public loadingTest = false;

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
            this.applicationService.testApplication(this.copy4Post(this.form.value)).subscribe((res) => {
                if (res.result == 0) {
                    this.applicationStatus = Number(JSON.stringify(res.data));
                }
            }, err => {
                console.log(err);
            });
            this.applicationService.addOrUpdate(this.copy4Post(this.form.value), true).subscribe((res) => {
                this.messageService.success("操作成功");
                this.router.navigate(["/list-application"]);
            });
        }
    }

    /**
     * 初始化方法
     */
    public ngOnInit(): void {
        this.form = this.fb.group({
            t_serverid: ["", [Validators.required]],
            t_applicationname: ["", [Validators.required, Validators.maxLength(32)]],
            t_developpath: ["", [Validators.required, Validators.maxLength(64)]],
            t_description: ["", [Validators.required, Validators.maxLength(255)]],
            t_addtime: ["", []],
            t_updatetime: ["", []],
            t_hostname: ["", []],
            t_host: ["", []],
        });
        this.serverService.selectByPage({curpage: 1, pagesize: 9999}).subscribe((data) => {
            (data.data as ServerPo[]).forEach((ele) => {
                this.servers.push(ele);
            })
        });
    }

    /**
     * 回退
     */
    public back() {
        this.router.navigate(["/list-application"]);
    }

    /**
     * 提交前元素内容拷贝
     */
    public copy4Post(application: ApplicationPo) {
        application.serverid = application.t_serverid;
        application.applicationname = application.t_applicationname;
        application.developpath = application.t_developpath;
        application.description = application.t_description;
        application.addtime = application.t_addtime;
        application.updatetime = application.t_updatetime;
        application.hostname = application.t_hostname;
        application.appstatus = this.applicationStatus;
        application.host = application.t_host;
        return application;
    }

    /**
     * 应用测试
     */
    public test() {
        if (this.form.value.t_developpath == '') {
            this.messageService.warning("应用部署路径不能为空！");
            return;
        }
        if (!new RegExp("^\\/(\\w+\\/?)+$").test(this.form.value.t_developpath)) {
            this.messageService.warning("应用部署路径有误！")
            return;
        }
        if (this.form.value.t_serverid == '') {
            this.messageService.warning("请选择对应的服务器！");
            return;
        }
        this.loadingTest = true;
        this.applicationService.testApplication(this.copy4Post(this.form.value)).subscribe((res) => {
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

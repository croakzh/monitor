import {Component, Injector} from "@angular/core";
import {BaseRecordComponent} from "../../components/zt-base/baseRecord.component";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LogService} from "../../services/log.service";
import {LogPo} from "../../models/logPo";
@Component({
    selector: "app-add-log",
    templateUrl: "./add-log.component.html",
    styleUrls: ["./add-log.component.scss"],
    host: {
        "[class.sd-content]": "true",
    },
})
export class AddLogComponent extends BaseRecordComponent<LogPo> {

    public form: FormGroup;

    constructor(protected injector: Injector, private fb: FormBuilder,
                protected logService: LogService) {
        super(injector, LogPo, logService);
        this.logService = injector.get(LogService);
    }

    public submitForm(): void {
        for (const i in this.form.controls) {
            this.form.controls[i].markAsDirty();
            this.form.controls[i].updateValueAndValidity();
        }
        if (this.form.valid) {
            this.logService.addOrUpdate(this.copy4Post(this.form.value), true).subscribe((res) => {
                this.messageService.success("操作成功");
                this.router.navigate(["/list-log"]);
            });
        }
    }

    /**
     * 初始化方法
     */
    public ngOnInit(): void {
        this.form = this.fb.group({
            t_detail: ["", []],
            t_serverid: ["", []],
            t_applicationid: ["", []],
            t_hostname: ["", []],
            t_host: ["", []],
            t_applicationname: ["", []],
        });
    }

    /**
     * 回退
     */
    public back() {
        this.router.navigate(["/list-log"]);
    }

    /**
     * 提交前元素内容拷贝
     */
    public copy4Post(log: LogPo) {
        log.detail = log.t_detail;
        log.serverid = log.t_serverid;
        log.applicationid = log.t_applicationid;
        log.hostname = log.t_hostname;
        log.host = log.t_host;
        log.applicationname = log.t_applicationname;
        return log;
    }

}

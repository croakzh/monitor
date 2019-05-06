import {Component, Injector} from "@angular/core";
import {BaseRecordComponent} from "../../components/zt-base/baseRecord.component";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LogService} from "../../services/log.service";
import {LogPo} from "../../models/logPo";
@Component({
    selector: "app-update-log",
    templateUrl: "./update-log.component.html",
    styleUrls: ["./update-log.component.scss"],
    host: {
        "[class.sd-content]": "true",
    },
})

export class UpdateLogComponent extends BaseRecordComponent<LogPo> {

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
            this.logService.addOrUpdate(this.form.value, false).subscribe((res) => {
                this.messageService.success("操作成功");
                this.router.navigate(["/list-log"]);
            });
        }
    }

    /**
     * 初始化方法
     */
    public ngOnInit(): void {
        const id = localStorage.getItem("id");
        this.form = this.fb.group({
            id: ["", []],
            detail: ["", []],
            serverid: ["", []],
            applicationid: ["", []],
            hostname: ["", []],
            host: ["", []],
            applicationname: ["", []],
        });
        const temp = new LogPo();
        temp.id = Number(id);
        this.logService.selectById(temp)
            .subscribe((data) => {
                this.form.setValue(this.copyContent(data.data as LogPo));
            });
    }

    /**
     * 回退
     */
    public back() {
        this.router.navigate(["/list-log"]);
    }
    public copyContent(log: LogPo) {
        log.id = this.transContent(log.id);
        log.detail = this.transContent(log.detail);
        log.serverid = this.transContent(log.serverid);
        log.applicationid = this.transContent(log.applicationid);
        log.hostname = this.transContent(log.hostname);
        log.host = this.transContent(log.host);
        log.applicationname = this.transContent(log.applicationname);
        return log;
    }

    public transContent(content: any) {
        if (content == null) {
            return "";
        }
        return content;
    }

}
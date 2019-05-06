import {Component, Injector} from "@angular/core";
import {LogService} from "../../services/log.service";
import {LogPo} from "../../models/logPo";
import {BaseRecordComponent} from "../../components/zt-base/baseRecord.component";
import {TableColumn} from "../../components/models/tableColumn";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ApplicationService} from "../../services/application.service";
import {ApplicationPo} from "../../models/applicationPo";
import {ServerPo} from "../../models/serverPo";
import {ServerService} from "../../services/server.service";

@Component({
    selector: "app-list-log",
    templateUrl: "./list-log.component.html",
    styleUrls: ["./list-log.component.scss"],
    host: {
        "[class.sd-content]": "true",
    },
})
export class ListLogComponent extends BaseRecordComponent<LogPo> {

    public contents = [];
    public servers = [];
    public applications = [];
    public searchForm: FormGroup;

    /**
     * modal框是否展示
     */
    public isVisible = false;
    public contentDetail = '';

    constructor(protected injector: Injector, protected logService: LogService,
                protected serverService: ServerService, protected applicationService: ApplicationService,
                private fb: FormBuilder) {
        super(injector, LogPo, logService);
        this.logService = injector.get(LogService);
        this.selectedRecord = new LogPo();
        this.serverService = injector.get(ServerService);
        this.applicationService = injector.get(ApplicationService);
    }

    /**
     * 重写父类 loadBaseData 方法，默认查询第一页
     */
    public loadBaseData() {
        super.loadBaseData();
        this.logService.selectByPage({curpage: 1, pagesize: 9999}).subscribe((data) => {
            (data.data as LogPo[]).forEach((ele) => {
                this.contents.push(ele);
            });
        });
        this.serverService.selectByPage({curpage: 1, pagesize: 9999}).subscribe((data) => {
            (data.data as ServerPo[]).forEach((ele) => {
                this.servers.push(ele);
            })
        });
        this.applicationService.selectByPage({curpage: 1, pagesize: 9999}).subscribe((data) => {
            (data.data as ApplicationPo[]).forEach((ele) => {
                this.applications.push(ele);
            })
        });
        this.searchForm = this.fb.group({
            condition: ["", []],
            selectServer: ["", []],
            selectApp: ["", []],
        });
    }

    /**
     * 构建表格 field
     */
    public buildTable() {
        this.columns.push({title: "日志详情", field: "detail", isSort: false} as TableColumn);
        this.columns.push({title: "服务器名", field: "hostname", isSort: false} as TableColumn);
        this.columns.push({title: "服务器ip", field: "host", isSort: false} as TableColumn);
        this.columns.push({title: "应用名称", field: "applicationname", isSort: false} as TableColumn);
    }

    /**
     * update跳转
     */
    public update(log: LogPo) {
        localStorage.removeItem("id");
        localStorage.setItem("id", log.id.toString());
        this.router.navigate(["update-log"]);
    }

    /**
     * add跳转
     */
    public add() {
        this.router.navigate(["/add-log"]);
    }

    public delete(log: LogPo) {
        this.modalService.confirm({
            nzTitle: "删除确认",
            nzContent: "是否要删除？",
            nzOnOk: () => {
                this.logService.deleteById(log).subscribe((res) => {
                    this.loadTableData();
                });
            },
            nzOnCancel: () => {
                //
            },
        });
    }

    public showDetail(data) {
        this.contentDetail = data['detail'];
        this.isVisible = true;

    }

    handleCancel(): void {
        this.isVisible = false;
    }

    /**
     * 重置
     */
    public resetForm() {
        this.searchForm.patchValue({condition: '', selectServer: '', selectApp: ''});
        this.search();
    }

    /**
     * 复杂检索
     */
    public search(): void {
        this.uiParams = {
            'term': this.searchForm.value.condition,
            'serverid': this.searchForm.value.selectServer,
            'applicationid': this.searchForm.value.selectApp,
            'curpage': 1,
            'pagesize': 9999
        };
        this.logService.selectByPage(this.uiParams).subscribe((data) => {
            this.contents = [];
            (data.data as LogPo[]).forEach((ele) => {
                this.contents.push(ele);
            });
            this.advSearch();
        });
    }

    /**
     * 选择服务器，更新应用
     */
    public selectServer(): void {
        this.applications = [];
        let dataInfo = {
            'curpage': 1,
            'pagesize': 9999,
            'serverid': this.searchForm.value.selectServer,
        };
        this.applicationService.selectByPage(dataInfo).subscribe((data) => {
            (data.data as ApplicationPo[]).forEach((ele) => {
                this.applications.push(ele);
            })
        });
        this.search();
    }

}

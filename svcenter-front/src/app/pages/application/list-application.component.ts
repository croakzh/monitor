import {Component, Injector} from "@angular/core";
import {ApplicationService} from "../../services/application.service";
import {ApplicationPo} from "../../models/applicationPo";
import {BaseRecordComponent} from "../../components/zt-base/baseRecord.component";
import {TableColumn} from "../../components/models/tableColumn";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ServerService} from "../../services/server.service";
import {ServerPo} from "../../models/serverPo";

@Component({
    selector: "app-list-application",
    templateUrl: "./list-application.component.html",
    styleUrls: ["./list-application.component.scss"],
    host: {
        "[class.sd-content]": "true",
    },
})
export class ListApplicationComponent extends BaseRecordComponent<ApplicationPo> {

    public contents = [];

    public searchForm: FormGroup;

    public isVisible = false;
    public applicationid: number;
    public fileList = [];
    public servers = [];

    constructor(protected injector: Injector, protected applicationService: ApplicationService,
                private serverService: ServerService, private fb: FormBuilder) {
        super(injector, ApplicationPo, applicationService);
        this.applicationService = injector.get(ApplicationService);
        this.serverService = injector.get(ServerService);
        this.selectedRecord = new ApplicationPo();
    }

    /**
     * 重写父类 loadBaseData 方法，默认查询第一页
     */
    public loadBaseData() {
        super.loadBaseData();
        this.applicationService.selectByPage({curpage: 1, pagesize: 9999}).subscribe((data) => {
            (data.data as ApplicationPo[]).forEach((ele) => {
                this.contents.push(ele);
            });
        });
        this.serverService.selectByPage({curpage: 1, pagesize: 9999}).subscribe((data) => {
            (data.data as ServerPo[]).forEach((ele) => {
                this.servers.push(ele);
            })
        });
        this.searchForm = this.fb.group({
            condition: ["", []],
            selectServer: ["", []],
            appstatus: ["", []],
        });
    }

    /**
     * 构建表格 field
     */
    public buildTable() {
        this.columns.push({title: "应用名称", field: "applicationname", isSort: false} as TableColumn);
        this.columns.push({title: "应用部署路径", field: "developpath", isSort: false} as TableColumn);
        this.columns.push({title: "描述", field: "description", isSort: false} as TableColumn);
        this.columns.push({title: "应用状态", field: "appstatus", isSort: false} as TableColumn);
        this.columns.push({title: "服务器名称", field: "hostname", isSort: false} as TableColumn);
        this.columns.push({title: "服务器ip", field: "host", isSort: false} as TableColumn);
    }

    /**
     * update跳转
     */
    public update(application: ApplicationPo) {
        localStorage.removeItem("id");
        localStorage.setItem("id", application.applicationid.toString());
        this.router.navigate(["update-application"]);
    }

    /**
     * 启停
     */
    public setup(application: ApplicationPo) {
        let content = '';
        let start = true;
        if (application.appstatus == 0) {
            content = '是否停止' + application.applicationname + "？";
            start = false;
        } else if (application.appstatus == 1) {
            content = '是否启动' + application.applicationname + "？";
        } else {
            this.messageService.info("任务进行中，请耐心等待...");
            return;
        }
        // console.log(start);
        this.modalService.confirm({
            nzTitle: "确认",
            nzContent: content,
            nzOnOk: () => {
                this.applicationService.setupApplication(application, start).subscribe((res) => {
                    this.messageService.success(res.message);
                    this.loadTableData();
                }, err => {
                    this.messageService.error(err.message);
                })
            },
            nzOnCancel: () => {
                //
            },
        });
    }

    /**
     * add跳转
     */
    public add() {
        this.router.navigate(["/add-application"]);
    }

    public delete(application: ApplicationPo) {
        this.modalService.confirm({
            nzTitle: "删除确认",
            nzContent: "是否要删除？",
            nzOnOk: () => {
                this.applicationService.deleteById(application).subscribe((res) => {
                    this.loadTableData();
                });
            },
            nzOnCancel: () => {
                //
            },
        });
    }

    /**
     * 重置
     */
    public resetForm() {
        this.searchForm.patchValue({condition: '', selectServer: '', appstatus: ''});
        this.search();
    }

    /**
     * 复杂检索
     */
    public search(): void {
        this.uiParams = {
            'term': this.searchForm.value.condition,
            'appstatus': this.searchForm.value.appstatus,
            'curpage': 1,
            'serverid': this.searchForm.value.selectServer,
            'pagesize': 9999
        };
        this.applicationService.selectByPage(this.uiParams).subscribe((data) => {
            this.contents = [];
            (data.data as ApplicationPo[]).forEach((ele) => {
                this.contents.push(ele);
            });
            this.advSearch();
        });
    }

    /**
     * 发布
     */
    public release(application: ApplicationPo): void {
        this.fileList = [];
        this.isVisible = true;
        this.applicationid = application.applicationid;
    }

    public handleCancel(): void {
        this.isVisible = false;
    }

    handleChange({file, fileList}): void {
        const status = file.status;
        if (status !== 'uploading') {
            console.log(file, fileList);
        }
        if (status === 'done') {
            this.messageService.success(`${file.name} 文件上传成功.`);
        } else if (status === 'error') {
            this.messageService.error(`${file.name} 文件上传失败.`);
        }
    }

    /**
     * 上传之前的检查
     */
    beforeUpload = (file: File) => {
        // console.log(this.fileList.length);
        if (this.fileList.length == 1) {
            this.messageService.error("只能上传一个文件");
            return false;
        }
        const fileName = file.name;
        let fileSuffix = '';
        if (fileName.includes(".")) {
            fileSuffix = fileName.substr(fileName.indexOf(".") + 1);
        }
        const isJar = (fileSuffix === 'jar' || fileSuffix === 'war');
        if (!isJar) {
            this.messageService.error("只能上传jar或war包");
        }
        // 大小限制
        return isJar;
    }

    /**
     * 提交发布
     */
    submitRelease() {
        if (this.fileList.length != 1) {
            this.messageService.error("请先上传发布文件！");
            return false;
        }

        let dataInfo = {
            "applicationid": this.applicationid,
            "name": this.fileList[0].response.name,
            "uid": this.fileList[0].response.uid,
        };
        let pageSpin = document.getElementById("pageSpin");
        pageSpin.style.display = 'block';
        this.applicationService.releaseApplication(dataInfo).subscribe(data => {
            if (data.result == 0) {
                pageSpin.style.display = 'none';
                this.messageService.success("发布成功");
                this.isVisible = false;
            }
        }, error1 => {
            console.log(error1);
            pageSpin.style.display = 'none';
            this.isVisible = false;
        });
    }

}

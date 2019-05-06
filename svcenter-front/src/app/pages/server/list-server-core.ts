import {BaseRecordComponent} from "../../components/zt-base/baseRecord.component";
import {ServerPo} from "../../models/serverPo";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Injector} from "@angular/core";
import {ServerService} from "../../services/server.service";
import {TableColumn} from "../../components/models/tableColumn";
import {BaseBean} from "../../models/baseBean";
import {GroupService} from "../../services/group.service";
import {GroupPo} from "../../models/groupPo";

export class ListServerCore<T extends BaseBean> extends BaseRecordComponent<ServerPo> {

    public groups = [];
    public contents = [];
    public searchForm: FormGroup;

    constructor(protected injector: Injector, protected serverService: ServerService,
                protected groupService: GroupService,
                protected fb: FormBuilder) {
        super(injector, ServerPo, serverService);
        this.serverService = injector.get(ServerService);
    }

    public loadBaseData() {
        super.loadBaseData();
        this.serverService.selectByPage({'curpage': 1, 'pagesize': 9999}).subscribe((data) => {
            (data.data as ServerPo[]).forEach((ele) => {
                this.contents.push(ele);
            });
        });
        this.groupService.selectByPage({curpage: 1, pagesize: 9999}).subscribe((data) => {
            (data.data as GroupPo[]).forEach((ele) => {
                this.groups.push(ele);
            })
        });
        this.searchForm = this.fb.group({
            condition: ["", []],
            selectGroup: ["", []],
            loginStatus: ["", []],
        });
    }

    public resetForm() {
        this.searchForm.patchValue({condition: '', selectGroup: '', loginStatus: ''});
        this.search();
    }

    public search(): void {
        this.uiParams = {
            'term': this.searchForm.value.condition,
            'groupid': this.searchForm.value.selectGroup,
            'status': this.searchForm.value.loginStatus,
            'curpage': 1,
            'pagesize': 9999
        };
        this.serverService.selectByPage(this.uiParams).subscribe((data) => {
            this.contents = [];
            (data.data as ServerPo[]).forEach((ele) => {
                this.contents.push(ele);
            });
            this.advSearch();
        });
    }


    public buildTable() {
        this.columns.push({title: "服务器ip", field: "host", isSort: false} as TableColumn);
        this.columns.push({title: "服务器名称", field: "hostname", isSort: false} as TableColumn);
        this.columns.push({title: "描述", field: "description", isSort: false} as TableColumn);
        this.columns.push({title: "连接端口", field: "sshport", isSort: false} as TableColumn);
        this.columns.push({title: "连接用户名", field: "sshname", isSort: false} as TableColumn);
        this.columns.push({title: "连接密码", field: "sshpwd", isSort: false} as TableColumn);
        this.columns.push({title: "登录状态", field: "status", isSort: false} as TableColumn);
        this.columns.push({title: "权限组名", field: "groupname", isSort: false} as TableColumn);
        this.columns.push({title: "自定义脚本集合", field: "shells", isSort: false} as TableColumn);
    }

    public update(server: ServerPo) {
        localStorage.removeItem("id");
        localStorage.setItem("id", server.serverid.toString());
        this.router.navigate(["update-server"]);
    }

    public add() {
        this.router.navigate(["/add-server"]);
    }

    public delete(server: ServerPo) {
        this.modalService.confirm({
            nzTitle: "删除确认",
            nzContent: "是否要删除服务器 " + server.host + "？",
            nzOnOk: () => {
                this.serverService.deleteById(server).subscribe((res) => {
                    this.loadTableData();
                });
            },
            nzOnCancel: () => {
                //
            },
        });
    }

}

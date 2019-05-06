import {Component, Injector} from "@angular/core";
import {GroupService} from "../../services/group.service";
import {GroupPo} from "../../models/groupPo";
import {BaseRecordComponent} from "../../components/zt-base/baseRecord.component";
import {TableColumn} from "../../components/models/tableColumn";

@Component({
    selector: "app-list-group",
    templateUrl: "./list-group.component.html",
    styleUrls: ["./list-group.component.scss"],
    host: {
        "[class.sd-content]": "true",
    },
})
export class ListGroupComponent extends BaseRecordComponent<GroupPo> {

    /*
     * 是否是新增页面
     */
    public isAdd = false;

    public contents = [];

    constructor(protected injector: Injector, protected groupService: GroupService) {
        super(injector, GroupPo, groupService);
        this.groupService = injector.get(GroupService);
        this.selectedRecord = new GroupPo();
    }

    /**
     * 重写父类 loadBaseData 方法，默认查询第一页
     */
    public loadBaseData() {
        super.loadBaseData();
        this.groupService.selectByPage({curpage: 1, pagesize: 9999}).subscribe((data) => {
            (data.data as GroupPo[]).forEach((ele) => {
                this.contents.push(ele);
            });
        });
    }

    /**
     * 构建表格 field
     */
    public buildTable() {
        this.columns.push({title: "组名", field: "groupname", isSort: false} as TableColumn);
        this.columns.push({title: "描述", field: "description", isSort: false} as TableColumn);
    }

    /**
     * update跳转
     */
    public update(group: GroupPo) {
        localStorage.removeItem("id");
        localStorage.setItem("id", group.groupid.toString());
        this.router.navigate(["update-group"]);
    }

    /**
     * add跳转
     */
    public add() {
        this.router.navigate(["/add-group"]);
    }

    public delete(group: GroupPo) {
        this.modalService.confirm({
            nzTitle: "删除确认",
            nzContent: "是否要删除组：" + group.groupname + "？",
            nzOnOk: () => {
                this.groupService.deleteById(group).subscribe((res) => {
                    this.loadTableData();
                });
            },
            nzOnCancel: () => {
                //
            },
        });
    }

}

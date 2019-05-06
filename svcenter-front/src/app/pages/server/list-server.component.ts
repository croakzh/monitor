import {Component, Injector} from "@angular/core";
import {ServerService} from "../../services/server.service";
import {GroupService} from "../../services/group.service";
import {NaturelogService} from "../../services/naturelog.service";
import {ServerPo} from "../../models/serverPo";
import {ListServerCore} from "./list-server-core";
import {FormBuilder} from "@angular/forms";

@Component({
    selector: "app-list-server",
    templateUrl: "./list-server.component.html",
    styleUrls: ["./list-server.component.scss"],
    host: {
        "[class.sd-content]": "true",
    },
})
export class ListServerComponent extends ListServerCore<ServerPo> {

    // public searchForm: FormGroup;

    // 模态框
    public isVisible = false;
    public contentDetail = '';
    public natureVisible = false;
    public naturelogDetail = [];

    constructor(protected injector: Injector, protected serverService: ServerService,
                protected groupService: GroupService,
                protected naturelogService: NaturelogService,
                protected fb: FormBuilder) {
        super(injector, serverService, groupService, fb);
        this.naturelogService = injector.get(NaturelogService);
    }

    /**
     *  <a class="zt-m5" (click)="detail(data)">详情</a>
     */
    public detail(server: ServerPo): void {
        let pageSpin = document.getElementById("pageSpin");
        pageSpin.style.display = 'block';
        let dataInfo = {
            'serverid': server.serverid,
        };
        this.naturelogService.selectById(dataInfo).subscribe((data) => {
            if (data.result == 0) {
                this.naturelogDetail = data.data;
                pageSpin.style.display = 'none';
                this.natureVisible = true;
            }
        }, err => {
            console.log(err);
            pageSpin.style.display = 'none';
        });

    }

    /**
     * <div id="changeColor" (click)="showDetail(data)">
     */
    public showDetail(data) {
        this.contentDetail = data['description'];
        this.isVisible = true;
    }

    /**
     * <nz-modal [(nzVisible)]="isVisible" nzTitle="服务器描述展示" (nzOnCancel)="handleCancel()" (nzOnOk)="handleCancel()"
     */
    public handleCancel(): void {
        this.isVisible = false;
    }

    /**
     * <nz-modal [(nzVisible)]="natureVisible" nzTitle="服务器性能实时查询展示" (nzOnCancel)="natureCancel()"
     *  (nzOnOk)="natureCancel()"
     */
    public natureCancel(): void {
        this.natureVisible = false;
    }

    /**
     * <a class="zt-m5" (click)="refreshLogin(data);">刷新状态</a>
     */
    public refreshLogin(server: ServerPo): void {
        let pageSpin = document.getElementById("pageSpin");
        pageSpin.style.display = 'block';
        let dataInfo = {
            'serverid': server.serverid,
        };
        this.serverService.refresh(dataInfo).subscribe((data) => {
            if (data.result == 0) {
                pageSpin.style.display = 'none';
                this.messageService.success("状态刷新成功");
            }
        }, err => {
            console.log(err);
            pageSpin.style.display = 'none';
        })
    }


}

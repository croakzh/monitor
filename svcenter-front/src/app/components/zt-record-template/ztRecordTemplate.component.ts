import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";

import {NzMessageService, NzModalService} from "ng-zorro-antd";
import {TreeMenu} from "../sidebar-menu/treeMenu";
import {AppService} from "../../app.service";
import {MENUS} from "../../menu";

/**
 * 主体组件
 */
@Component({
    selector: "zt-record-template",
    templateUrl: "./ztRecordTemplate.component.html",
    styleUrls: ["./ztRecordTemplate.component.scss"],
})
export class ZtRecordTemplateComponent implements OnInit {

    public title: string = "首页";
    public menus: TreeMenu[] = [];
    // 当前url的菜单编号
    private menuCode: string;

    constructor(private router: Router,
                private modalService: NzModalService,
                private messageService: NzMessageService,
                private appService: AppService) {
        const strs = this.router.url.split("/");
        if (strs.length > 1) {
            this.menuCode = strs[1];
            this.loadMenus();
        } else {
            messageService.error("系统错误，未知URL");
        }
    }

    public ngOnInit() {
    }

    // 加载菜单列表
    private loadMenus() {
        this.menus = MENUS;
    }

}

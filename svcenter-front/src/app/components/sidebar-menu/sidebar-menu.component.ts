import { Component, Input, OnInit } from "@angular/core";
import { Router, NavigationEnd } from "@angular/router";
import { TreeMenu } from "./treeMenu";

/**
 * 左侧菜单组件
 */
@Component({
    selector: "zt-sidebar-menu",
    template: `
  <div class="zt-nav-top">
    <a style="background-color:black;">
      <span class="nav-label" style="color:#fff;font-weight:normal">网站导航</span>
      <i class="fa icon-nav" (click)="toggleCollapsed()"></i>
    </a>
  </div>
  <div class="zt-nav" c-custom-scrollbar>
    <ul nz-menu [nzMode]="'inline'">
        <li nz-menu-item *ngFor="let item of data" (click)="clickMenu(item)">
            <span title>
                <span>{{item.value}}</span>
            </span>
        </li>
  </ul>
  </div>
  `,
    styleUrls: ["./sidebar-menu.component.scss"],
    host: {
        "[class.collasped]": "isCollapsed",
    },
})

export class SidebarMenuComponent implements OnInit {
    // 传入参数
    @Input() public data: TreeMenu[];

    public isCollapsed: boolean = false;
    /**
     * 构造方法
     */
    constructor(private router: Router) {
        //
    }

    /**
     * 初始化
     */
    public ngOnInit() {
        this.router.events
        .subscribe((event) => {
            if (event instanceof NavigationEnd) { // 当导航成功结束时执行
                for (const menu of this.data) {
                }
            }
        });

    }

    // 点击菜单
    public clickMenu(menu: TreeMenu) {
        this.router.navigate([menu.routerPath]);
    }

    public getNavigateUrl(oriUrl) {
        if (oriUrl.indexOf("#/") < 0) {return oriUrl; }
        return oriUrl.substr(oriUrl.indexOf("#/") + 2);
    }

    // 切换菜单收缩展开状态
    public toggleCollapsed() {
        this.isCollapsed = !this.isCollapsed;
    }

    public iconImage(menu) {
        let icon = menu.value.icon;

        if (icon == null) { icon = "icon-" + menu.value.rightno; }
        return "assets/menu_icon/" + icon + ".png";
    }

}

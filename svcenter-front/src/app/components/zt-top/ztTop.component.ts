import {AfterViewInit, Component, ElementRef, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {NzMessageService, NzModalService} from "ng-zorro-antd";

/**
 * 主体组件
 */
@Component({
    selector: "zt-top",
    templateUrl: "./ztTop.component.html",
    styleUrls: ["./ztTop.component.scss"],
})
export class ZtTopComponent implements OnInit, AfterViewInit {

    constructor(private router: Router,
                private messageService: NzMessageService,
                private elementRef: ElementRef) {
    }

    public ngOnInit() {
    }

    public ngAfterViewInit() {
    }

    public logout = function () {
    };

    public gotoProfile() {
    }

    public changePwd() {
    }

    public gohome() {
        // this.router.navigate(["/"]);
    }
}

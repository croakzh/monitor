import {Component, Injector} from "@angular/core";
import {FormBuilder} from "@angular/forms";
import {ServerService} from "../../services/server.service";
import {ServerPo} from "../../models/serverPo";
import {GroupService} from "../../services/group.service";
import {AddServerCore} from "./add-server-core";

@Component({
    selector: "app-add-server",
    templateUrl: "./add-server.component.html",
    styleUrls: ["./add-server.component.scss"],
    host: {
        "[class.sd-content]": "true",
    },
})
export class AddServerComponent extends AddServerCore<ServerPo> {

    public loadingTest = false;

    constructor(protected injector: Injector, protected fb: FormBuilder,
                protected groupService: GroupService,
                protected serverService: ServerService) {
        super(injector, fb, groupService, serverService);
    }

    /**
     * 测试连接
     */
    public test() {
        let pageSpin = document.getElementById("pageSpin");
        pageSpin.style.display = 'block';
        this.loadingTest = true;
        this.serverService.testConnect(this.copy4Post(this.form.value)).subscribe((res) => {
            if (res.result == 0) {
                this.loadingTest = false;
                pageSpin.style.display = 'none';
                this.messageService.success("连接服务器成功！");
            }
        }, err => {
            console.log(err);
            pageSpin.style.display = 'none';
            this.loadingTest = false;
        })
    }

}

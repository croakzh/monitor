import {BaseBean} from "../../models/baseBean";
import {BaseRecordComponent} from "../../components/zt-base/baseRecord.component";
import {ServerPo} from "../../models/serverPo";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Injector} from "@angular/core";
import {ServerService} from "../../services/server.service";
import {GroupService} from "../../services/group.service";
import {GroupPo} from "../../models/groupPo";


export class UpdateServerCore<T extends BaseBean> extends BaseRecordComponent<ServerPo> {

    public form: FormGroup;
    public groups = [];

    constructor(protected injector: Injector, protected fb: FormBuilder,
                protected serverService: ServerService, protected groupService: GroupService) {
        super(injector, ServerPo, serverService);
        this.serverService = injector.get(ServerService);
        this.groupService = injector.get(GroupService);
    }

    public submitForm(): void {
        for (const i in this.form.controls) {
            this.form.controls[i].markAsDirty();
            this.form.controls[i].updateValueAndValidity();
        }
        if (this.form.valid) {
            this.serverService.addOrUpdate(this.copy4Post(this.form.value), false).subscribe((res) => {
                this.messageService.success("操作成功");
                this.router.navigate(["/list-server"]);
            });
        }
    }

    /**
     * 初始化方法
     */
    public ngOnInit(): void {
        const id = localStorage.getItem("id");
        this.form = this.fb.group({
            serverid: ["", []],
            groupid: ["", [Validators.required]],
            host: ["", [Validators.required]],
            hostname: ["", [Validators.required, Validators.maxLength(64)]],
            description: ["", [Validators.maxLength(255)]],
            sshport: ["", [Validators.required]],
            sshname: ["", [Validators.required, Validators.maxLength(64)]],
            sshpwd: ["", [Validators.required, Validators.maxLength(64)]],
            addtime: ["", []],
            status: ["", []],
            shells: ["", []],
            updatetime: ["", []],
        });
        const temp = new ServerPo();
        temp.serverid = Number(id);
        this.serverService.selectById(temp)
            .subscribe((data) => {
                this.form.setValue(this.copyContent(data.data as ServerPo));
            });

        this.groupService.selectByPage({curpage: 1, pagesize: 9999}).subscribe((res) => {
            (res.data as GroupPo[]).forEach((ele) => {
                this.groups.push(ele);
            })
        })
    }

    /**
     * 回退
     */
    public back() {
        this.router.navigate(["/list-server"]);
    }

    public copyContent(server: ServerPo) {
        server.serverid = this.transContent(server.serverid);
        server.groupid = this.transContent(server.groupid);
        server.host = this.transContent(server.host);
        server.hostname = this.transContent(server.hostname);
        server.description = this.transContent(server.description);
        server.sshport = this.transContent(server.sshport);
        server.sshname = this.transContent(server.sshname);
        server.sshpwd = this.transContent(server.sshpwd);
        server.addtime = this.transContent(server.addtime);
        server.updatetime = this.transContent(server.updatetime);
        server.shells = this.transContent(server.shells);
        return server;
    }

    /**
     * 提交前元素内容拷贝
     */
    public copy4Post(server: ServerPo) {
        server.serverid = this.transContent(server.serverid);
        server.groupid = this.transContent(server.groupid);
        server.host = this.transContent(server.host);
        server.hostname = this.transContent(server.hostname);
        server.description = this.transContent(server.description);
        server.sshport = this.transContent(server.sshport);
        server.sshname = this.transContent(server.sshname);
        server.sshpwd = this.transContent(server.sshpwd);
        server.addtime = this.transContent(server.addtime);
        server.updatetime = this.transContent(server.updatetime);
        server.shells = this.transContent(server.shells);
        return server;
    }

    public transContent(content: any) {
        if (content == null) {
            return "";
        }
        return content;
    }


}

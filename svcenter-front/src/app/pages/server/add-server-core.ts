import {BaseBean} from "../../models/baseBean";
import {BaseRecordComponent} from "../../components/zt-base/baseRecord.component";
import {ServerPo} from "../../models/serverPo";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Injector} from "@angular/core";
import {ServerService} from "../../services/server.service";
import {GroupService} from "../../services/group.service";
import {GroupPo} from "../../models/groupPo";

export class AddServerCore<T extends BaseBean> extends BaseRecordComponent<ServerPo> {
    public form: FormGroup;
    public groups = [];

    constructor(protected injector: Injector, protected fb: FormBuilder,
                protected groupService: GroupService,
                protected serverService: ServerService) {
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
            this.serverService.addOrUpdate(this.copy4Post(this.form.value), true).subscribe((res) => {
                this.messageService.success("操作成功");
                this.router.navigate(["/list-server"]);
            });
        }
    }

    /**
     * 初始化方法
     */
    public ngOnInit(): void {
        this.form = this.fb.group({
            t_groupid: ["", [Validators.required]],
            t_host: ["", [Validators.required]],
            t_hostname: ["", [Validators.required, Validators.maxLength(64)]],
            t_description: ["", [Validators.maxLength(255)]],
            t_sshport: ["", [Validators.required]],
            t_sshname: ["", [Validators.required, Validators.maxLength(64)]],
            t_sshpwd: ["", [Validators.required, Validators.maxLength(64)]],
            t_status: ["", []],
            t_shells: ["", []],
            t_addtime: ["", []],
            t_updatetime: ["", []],
            t_groupname: ["", []],
        });
        this.groupService.selectByPage({curpage: 1, pagesize: 9999}).subscribe((data) => {
            (data.data as GroupPo[]).forEach((ele) => {
                this.groups.push(ele);
            })
        });
    }

    /**
     * 回退
     */
    public back() {
        this.router.navigate(["/list-server"]);
    }

    /**
     * 提交前元素内容拷贝
     */
    public copy4Post(server: ServerPo) {
        server.groupid = server.t_groupid;
        server.host = server.t_host;
        server.hostname = server.t_hostname;
        server.description = server.t_description;
        server.sshport = server.t_sshport;
        server.sshname = server.t_sshname;
        server.sshpwd = server.t_sshpwd;
        server.status = server.t_status;
        server.shells = server.t_shells;
        server.addtime = server.t_addtime;
        server.updatetime = server.t_updatetime;
        server.groupname = server.t_groupname;
        return server;
    }

}

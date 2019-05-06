import {Component, Injector} from "@angular/core";
import {BaseRecordComponent} from "../../components/zt-base/baseRecord.component";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {GroupService} from "../../services/group.service";
import {GroupPo} from "../../models/groupPo";
@Component({
    selector: "app-update-group",
    templateUrl: "./update-group.component.html",
    styleUrls: ["./update-group.component.scss"],
    host: {
        "[class.sd-content]": "true",
    },
})

export class UpdateGroupComponent extends BaseRecordComponent<GroupPo> {

    public form: FormGroup;

    constructor(protected injector: Injector, private fb: FormBuilder,
                     protected groupService: GroupService) {
        super(injector, GroupPo, groupService);
        this.groupService = injector.get(GroupService);
    }

    public submitForm(): void {
        for (const i in this.form.controls) {
            this.form.controls[i].markAsDirty();
            this.form.controls[i].updateValueAndValidity();
        }
        if (this.form.valid) {
            this.groupService.addOrUpdate(this.form.value, false).subscribe((res) => {
                this.messageService.success("操作成功");
                this.router.navigate(["/list-group"]);
            });
        }
    }

    /**
     * 初始化方法
     */
    public ngOnInit(): void {
        const id = localStorage.getItem("id");
        this.form = this.fb.group({
            groupid: ["", []],
            groupname: ["", [Validators.required, Validators.maxLength(64)]],
            description: ["", []],
            addtime: ["", []],
            updatetime: ["", []],
        });
        const temp = new GroupPo();
        temp.groupid = Number(id);
        this.groupService.selectById(temp)
            .subscribe((data) => {
                this.form.setValue(this.copyContent(data.data as GroupPo));
            });
    }

    /**
     * 回退
     */
    public back() {
        this.router.navigate(["/list-group"]);
    }
    public copyContent(group: GroupPo) {
        group.groupid = this.transContent(group.groupid);
        group.groupname = this.transContent(group.groupname);
        group.description = this.transContent(group.description);
        group.addtime = this.transContent(group.addtime);
        group.updatetime = this.transContent(group.updatetime);
        return group;
    }

    public transContent(content: any) {
        if (content == null) {
            return "";
        }
        return content;
    }

}

import {Component, Injector} from "@angular/core";
import {BaseRecordComponent} from "../../components/zt-base/baseRecord.component";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {GroupService} from "../../services/group.service";
import {GroupPo} from "../../models/groupPo";
@Component({
    selector: "app-add-group",
    templateUrl: "./add-group.component.html",
    styleUrls: ["./add-group.component.scss"],
    host: {
        "[class.sd-content]": "true",
    },
})
export class AddGroupComponent extends BaseRecordComponent<GroupPo> {

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
            this.groupService.addOrUpdate(this.copy4Post(this.form.value), true).subscribe((res) => {
                this.messageService.success("操作成功");
                this.router.navigate(["/list-group"]);
            });
        }
    }

    /**
     * 初始化方法
     */
    public ngOnInit(): void {
        this.form = this.fb.group({
            t_groupname: ["", [Validators.required, Validators.maxLength(64)]],
            t_description: ["", []],
            t_addtime: ["", []],
            t_updatetime: ["", []],
        });
    }

    /**
     * 回退
     */
    public back() {
        this.router.navigate(["/list-group"]);
    }

    /**
     * 提交前元素内容拷贝
     */
    public copy4Post(group: GroupPo) {
        group.groupname = group.t_groupname;
        group.description = group.t_description;
        group.addtime = group.t_addtime;
        group.updatetime = group.t_updatetime;
        return group;
    }

}

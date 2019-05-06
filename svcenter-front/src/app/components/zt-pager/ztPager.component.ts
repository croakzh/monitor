import { Component, EventEmitter, forwardRef, Input, Output } from "@angular/core";

import { ControlValueAccessor, NG_VALUE_ACCESSOR } from "@angular/forms";
import { TableData } from "../models/tableData";

const ZT_PAGER_CONTROL_VALUE_ACCESSOR: any = {
    provide: NG_VALUE_ACCESSOR,
    useExisting: forwardRef(() => ZtPagerComponent),
    multi: true,
};

@Component({
    selector: "zt-pager",
    template: `
        <span class="pager-total">共{{totalRecords}}笔数据</span>
        <nz-pagination
        [nzTotal]="totalRecords"
        nzShowSizeChanger
        (nzPageIndexChange)="pageIndexChange($event)"
        (nzPageSizeChange)="pageSizeChange($event)"
        [(nzPageIndex)]="pageIndex"
        [(nzPageSize)]="pageSize">
        </nz-pagination>
    `,
    styleUrls: ["./ztPager.component.scss"],
    providers: [ZT_PAGER_CONTROL_VALUE_ACCESSOR],
})
export class ZtPagerComponent {

    @Input()
    get nzPageIndex(): number {
        return this.pageIndex;
    }
    set nzPageIndex(pageIndex: number) {
        this.pageIndex = pageIndex;
    }

    @Input()
    get nzPageSize(): number {
        return this.pageSize;
    }
    set nzPageSize(pageSize: number) {
        this.pageSize = pageSize;
    }

    @Input()
    set nzTotal(total: number) {
        this.totalRecords = total;
    }

    @Output()
    public nzPageIndexChange = new EventEmitter<number>();

    @Output()
    public nzPageSizeChange = new EventEmitter<number>();

    public pageIndex: number;
    public pageSize: number;
    public totalRecords: number;

    public pageIndexChange(index) {
        this.pageIndex = index;
        this.nzPageIndexChange.emit(this.pageIndex);
    }

    public pageSizeChange(size: number) {
        this.pageSize = size;
        this.nzPageSizeChange.emit(this.pageSize);
    }

}

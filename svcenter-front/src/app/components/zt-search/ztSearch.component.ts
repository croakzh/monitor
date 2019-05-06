import { Component, EventEmitter, forwardRef, Input, Output } from "@angular/core";

import { ControlValueAccessor, NG_VALUE_ACCESSOR } from "@angular/forms";

const ZT_SEARCH_CONTROL_VALUE_ACCESSOR: any = {
    provide: NG_VALUE_ACCESSOR,
    useExisting: forwardRef(() => ZtSearchComponent),
    multi: true,
};

@Component({
    selector: "zt-search",
    template: `
            <div class="zt-input">
                <input type="text" class="" placeholder="{{placeholder}}" (ngModelChange)="myChange($event)" (keyup.enter)="mySearch()" [(ngModel)]="ngModel">
                <img src="../../../assets/img/search.png" style="cursor: pointer;" (click)="mySearch();">
            </div>
    `,
    styleUrls: ["./ztSearch.component.scss"],
    providers: [ZT_SEARCH_CONTROL_VALUE_ACCESSOR],
})
export class ZtSearchComponent implements ControlValueAccessor {

    @Input()
    public placeholder: string = "";

    @Input()
    public value: string = "";

    public ngModel: string = "";

    @Output() public onSearch = new EventEmitter<string>();

    public writeValue(value: any): void {
        this.ngModel = value;
    }

    public registerOnChange(fn: any) {
        this.onChangeCallback = fn;
    }

    public mySearch() {
        this.onSearch.emit(this.ngModel);
    }

    public myChange($event) {
        // 通知父组件，属性发生改变
        this.onChangeCallback($event);
    }

    public registerOnTouched(fn: any) {
        this.onTouchedCallback = fn;
    }

    private onTouchedCallback = (v: any) => {
        //
    }
    private onChangeCallback = (v: any) => {
        //
    }

}

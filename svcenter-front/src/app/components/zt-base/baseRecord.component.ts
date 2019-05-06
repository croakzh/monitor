import {Injector, OnInit} from "@angular/core";
import {BaseService} from "../../services/base.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Observable} from "rxjs";
import {rSResult} from "../../models/rSResult";
import {TableColumn} from "../models/tableColumn";
import {TableData} from "../models/tableData";
import {NzMessageService, NzModalService} from "ng-zorro-antd";
import {BasePageComponent} from "./basePage.component";
import {BaseBean} from "../../models/baseBean";

// 详情页面基类
export abstract class BaseRecordComponent<T extends BaseBean> extends BasePageComponent implements OnInit {
    // 列定义
    public columns: TableColumn[] = [];
    // 表格数据
    public tableData: TableData<T> = new TableData();
    // 是否全选
    public allChecked = false;
    // 按钮是否禁用
    public disabledButton = true;

    // 界面参数
    public uiParams: any = {};
    // 查询参数
    public queryParams: any = {};

    // 是否展示全部搜索项
    public showAllSearchTypes = false;

    // 主键
    public id: any;
    public idField: string;
    // 选中的记录
    public selectedRecord: T;

    // Promises
    public basePromises: Array<Promise<void>> = [];

    // 服务
    protected router: Router;
    protected modalService: NzModalService;
    protected messageService: NzMessageService;
    protected activatedRoute: ActivatedRoute;

    // 构造函数
    constructor(
        protected injector: Injector,
        protected detailType: new () => T,
        protected dataService: BaseService<T>,
    ) {

        super(injector);

        this.idField = (new this.detailType()).ztIdField;
    }

    /**
     * 初始化
     */
    public ngOnInit() {
        // 初始化
        this.onInit();

        this.initParam();

        // 加载基础数据
        this.loadBaseData();

        // 构建表格
        this.buildTable();

        // 加载表格数据
        Promise.all(this.basePromises).then(() => {
            this.loadTableData();
        });
    }

    // 初始化参数
    public initParam() {
        //
    }

    // 初始化
    public onInit() {
        // 初始化
    }

    // 加载基础数据
    public loadBaseData() {
        //
    }

    // 构建表格
    public buildTable() {
        //
    }

    // 高级查询
    public extendSearchTypes() {
        this.showAllSearchTypes = !this.showAllSearchTypes;
    }

    // 加载表格数据
    public loadTableData() {
        this.buildQueryParams();
        this.tableData.loading = true;
        this.doLoadTableData().subscribe((result) => {
            this.onTableDataLoaded(result);
        });
    }

    // 加载表格数据
    public doLoadTableData(): Observable<rSResult<T>> {
        // console.log(this.dataService)
        // console.log(this.queryParams)
        return this.dataService.selectByPage(this.queryParams);
    }

    // 表格数据加载成功后
    public onTableDataLoaded(result: rSResult<T>) {
        this.tableData.total = result.total;
        this.tableData.loading = false;
        this.tableData.data = result.data as T[];

        this.refreshStatus();
    }

    // 高级查询
    public advSearch() {
        // 清空之前的查询条件
        this.queryParams = {};
        // 从界面参数中取出本次的查询条件
        for (const key in this.uiParams) {
            if (this.uiParams.hasOwnProperty(key)) {
                this.queryParams[key] = this.uiParams[key];
            }
        }
        // 刷新数据
        this.loadTableData();
    }

    // 清除搜索
    public clearSearch() {
        this.uiParams = {};
        // 清空之前的查询条件
        this.queryParams = {};
        // 刷新数据
        this.loadTableData();

    }

    // 构建查询参数
    public buildQueryParams() {
        this.queryParams.curpage = this.tableData.curpage;
        this.queryParams.pagesize = this.tableData.pagesize;
    }

    /**
     * 排序
     */
    public sort(sort: { key: string, value: string }) {
        const desc = sort.value === "descend" ? "desc" : "";
        this.queryParams.sortord = sort.key + " " + desc;
        this.loadTableData();
    }

    // 打开编辑页
    public openUpdate(id) {
        this.router.navigate([this.router.url, id]);
    }

    // 删除单笔记录
    public deleteRecord(params: any) {
        this.beforeDeleteRecord(params).subscribe((result1) => {
            if (!result1) {
                return;
            }

            this.doDeleteRecord(params).subscribe((result2) => {
                this.afterDeleteRecord(result2);
            });
        });
    }

    // 删除前处理
    public beforeDeleteRecord(params): Observable<boolean> {
        return Observable.create((observer) => {
            this.modalService.confirm({
                nzTitle: "删除确认",
                nzContent: "确定要删除该记录吗？",
                nzOnOk: () => {
                    observer.next(true);
                    observer.complete();
                },
                nzOnCancel: () => {
                    observer.next(false);
                    observer.complete();
                },
            });
        });
    }

    // 删除
    public doDeleteRecord(params): Observable<rSResult<T>> {
        return this.dataService.deleteById(params);
    }

    // 删除后处理
    public afterDeleteRecord(result: rSResult<T>) {
        this.loadTableData();
    }

    // 获取选中记录
    public getSelectedRecords(): any[] {
        const ids = [];

        this.tableData.data.filter((value) => value.ztChecked).forEach((data) => ids.push(data[this.idField]));

        return ids;
    }

    // 批量删除
    public deleteRecords() {
        const ids = this.getSelectedRecords();

        this.beforeDeleteRecords(ids).subscribe((result1) => {
            if (!result1) {
                return;
            }

            this.doDeleteRecords(ids).subscribe((result2) => {
                this.afterDeleteRecords(result2);
            });
        });
    }

    // 批量删除前处理
    public beforeDeleteRecords(ids): Observable<boolean> {
        return Observable.create((observer) => {
            this.modalService.confirm({
                nzTitle: "删除确认",
                nzContent: "确定要删除选中的记录吗？",
                nzOnOk: () => {
                    observer.next(true);
                    observer.complete();
                },
                nzOnCancel: () => {
                    observer.next(false);
                    observer.complete();
                },
            });
        });
    }

    // 批量删除
    public doDeleteRecords(ids): Observable<rSResult<string>> {
        return this.dataService.deleteByIds(ids);
    }

    // 批量删除后处理
    public afterDeleteRecords(result: rSResult<string>) {
        this.loadTableData();
    }

    // 刷新勾选时相应的状态
    public refreshStatus(): void {
        this.allChecked = this.tableData.data.every((value) => value.ztChecked === true);

        this.disabledButton = !this.tableData.data.some((value) => value.ztChecked);
    }

    // 全选
    public checkAll(value: boolean) {
        this.tableData.data.forEach((data) => data.ztChecked = value);
        this.refreshStatus();
    }

    // 选择记录
    public selectRecord(record) {
        this.dataService.selectById(record).subscribe((res) => {
            this.selectedRecord = res.data as T;
            this.tableData.showDetail = true;
        });
    }

    public closeDetailWin() {
        this.tableData.showDetail = false;
    }

}

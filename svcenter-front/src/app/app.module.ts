import {HttpClientModule} from "@angular/common/http";
import {NgModule} from "@angular/core";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {BrowserModule} from "@angular/platform-browser";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
// app
import {AppComponent} from "./app.component";
import {AppService} from "./app.service";
// modules
import {AppRoutingModule} from "./app-routing.module";
import {registerLocaleData} from "@angular/common";
import zh from "@angular/common/locales/zh";
import {NgZorroAntdModule, NZ_I18N, zh_CN} from "ng-zorro-antd";
import {ZtRecordTemplateComponent} from "./components/zt-record-template/ztRecordTemplate.component";
import {FieldRender} from "./pipes/fieldRender.pipe";
import {StatusRender} from "./pipes/statusRender.pipe";
import {SetRender} from "./pipes/setRender.pipe";
import {Dic2Array} from "./pipes/dic2Array.pipe";
import {ZtSearchComponent} from "./components/zt-search/ztSearch.component";
import {HttpClientService} from "./services/httpClient.service";
import {ZtPagerComponent} from "./components/zt-pager/ztPager.component";
import {ValidService} from "./utils/valid.service";
import {SidebarMenuComponent} from "./components/sidebar-menu/sidebar-menu.component";
import {ZtTopComponent} from "./components/zt-top/ztTop.component";
import {UEditorModule} from "ngx-ueditor";
// start-import-component
import {ListLogComponent} from "./pages/log/list-log.component";
import {AddLogComponent} from "./pages/log/add-log.component";
import {UpdateLogComponent} from "./pages/log/update-log.component";
import {ListApplicationComponent} from "./pages/application/list-application.component";
import {AddApplicationComponent} from "./pages/application/add-application.component";
import {UpdateApplicationComponent} from "./pages/application/update-application.component";
import {ListGroupComponent} from "./pages/group/list-group.component";
import {AddGroupComponent} from "./pages/group/add-group.component";
import {UpdateGroupComponent} from "./pages/group/update-group.component";
import {ListServerComponent} from "./pages/server/list-server.component";
import {AddServerComponent} from "./pages/server/add-server.component";
import {UpdateServerComponent} from "./pages/server/update-server.component";
// end-import-component
// start-import-service
import {LogService} from "./services/log.service";
import {ApplicationService} from "./services/application.service";
import {GroupService} from "./services/group.service";
import {NaturelogService} from "./services/naturelog.service";
import {ServerService} from "./services/server.service";
// end-import-service

registerLocaleData(zh);

/**
 * app模块
 */
@NgModule({
    imports: [
        AppRoutingModule,
        BrowserModule,
        BrowserAnimationsModule,
        HttpModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        NgZorroAntdModule,
        UEditorModule.forRoot({
            js: [
                `../assets/ueditor/ueditor.all.min.js`,
                `../assets/ueditor/ueditor.config.js`,
            ],
            // 默认前端配置项
            options: {
                UEDITOR_HOME_URL: "../assets/ueditor/",
            },
        }),
    ],
    declarations: [
        AppComponent,
        ZtTopComponent,
        SidebarMenuComponent,
        ZtRecordTemplateComponent,
        FieldRender,
        StatusRender,
        SetRender,
        Dic2Array,
        ZtSearchComponent,
        ZtPagerComponent,
        // start-component
        ListLogComponent,
        AddLogComponent,
        UpdateLogComponent,
        ListApplicationComponent,
        AddApplicationComponent,
        UpdateApplicationComponent,
        ListGroupComponent,
        AddGroupComponent,
        UpdateGroupComponent,
        ListServerComponent,
        AddServerComponent,
        UpdateServerComponent,
        // end-component
    ],
    providers: [
        AppService,
        HttpClientService,
        ValidService,
        // start-service
        LogService,
        ApplicationService,
        GroupService,
        ServerService,
        NaturelogService,
        // end-service
        {provide: NZ_I18N, useValue: zh_CN},
    ],
    exports: [],
    bootstrap: [AppComponent],
})
export class AppModule {
}

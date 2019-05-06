import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";

import {ZtRecordTemplateComponent} from "./components/zt-record-template/ztRecordTemplate.component";
// start-import
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
// end-import

/**
 * app路由
 */
const appRoutes: Routes = [
    {
        path: "",
        component: ZtRecordTemplateComponent,
        children: [
            // start-path
            {path: "list-log", component: ListLogComponent},
            {path: "add-log", component: AddLogComponent},
            {path: "update-log", component: UpdateLogComponent},
            {path: "list-application", component: ListApplicationComponent},
            {path: "add-application", component: AddApplicationComponent},
            {path: "update-application", component: UpdateApplicationComponent},
            {path: "list-group", component: ListGroupComponent},
            {path: "add-group", component: AddGroupComponent},
            {path: "update-group", component: UpdateGroupComponent},
            {path: "list-server", component: ListServerComponent},
            {path: "add-server", component: AddServerComponent},
            {path: "update-server", component: UpdateServerComponent},
            // end-path
        ],
    },
];

@NgModule({
    imports: [
        RouterModule.forRoot(appRoutes, {useHash: true}),
    ],
    exports: [
        RouterModule,
    ],
})
export class AppRoutingModule {
}

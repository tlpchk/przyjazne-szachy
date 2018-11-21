import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./login.component";
import {NgModule} from "@angular/core";
import {AdminComponent} from "./admin/admin.component";


const routes: Routes = [
    {
        path: 'login', component: LoginComponent,
        children: []
    },
    {   path: 'admin', component: AdminComponent,
        children: []
    }
];

@NgModule({
    imports: [ RouterModule.forChild(routes) ],
    exports: [ RouterModule ]
})

export class LoginRoutingModule { }

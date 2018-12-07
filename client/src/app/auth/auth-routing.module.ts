import {RouterModule, Routes} from "@angular/router";
import {AuthComponent} from "./auth.component";
import {NgModule} from "@angular/core";
import {UserComponent} from "../user/user.component";
import {HomeComponent} from "../user/home/home.component";
import {BoardComponent} from "../user/board/board.component";
import {LoginComponent} from "./login/login.component";
import {RegistComponent} from "./regist/regist.component";


const routes: Routes = [
    {
        path: 'auth', component: AuthComponent,
        children: [
            {
                path: '',
                redirectTo: '/login',
                pathMatch: 'full'
            },
            {
                path: 'login',
                component: LoginComponent

            },
            {
                path: 'regist',
                component: RegistComponent
            },
        ]
    },
];

@NgModule({
    imports: [ RouterModule.forChild(routes) ],
    exports: [ RouterModule ]
})

export class AuthRoutingModule { }

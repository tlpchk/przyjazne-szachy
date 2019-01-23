import {RouterModule, Routes} from "@angular/router";
import {AuthComponent} from "./auth.component";
import {NgModule} from "@angular/core";
import {LoginComponent} from "./login/login.component";
import {RegistComponent} from "./regist/regist.component";
import {VerifyMailComponent} from "./verify-mail/verify-mail.component";
import {ResetPasswordComponent} from "./reset-password/reset-password.component";
import {ResetPasswordMailSenderComponent} from "./reset-password-mail-sender/reset-password-mail-sender.component";


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
            {
                path: 'verify-mail',
                component: VerifyMailComponent
            },
            {
                path: 'reset-password',
                component: ResetPasswordComponent
            },
            {
                path: 'reset-mail',
                component: ResetPasswordMailSenderComponent
            },
        ]
    },
];

@NgModule({
    imports: [ RouterModule.forChild(routes) ],
    exports: [ RouterModule ]
})

export class AuthRoutingModule { }

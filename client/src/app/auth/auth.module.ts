import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AuthComponent} from "./auth.component";
import {AuthRoutingModule} from "./auth-routing.module";
import {AuthServicePS} from "../_services/auth-service-p-s.service";
import {LoginComponent} from "./login/login.component";
import {RegistComponent} from "./regist/regist.component";
import {FormsModule} from "@angular/forms";
import { VerifyMailComponent } from './verify-mail/verify-mail.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';

@NgModule({
    imports: [
        CommonModule,
        AuthRoutingModule,
        FormsModule,
    ],
    declarations: [
        AuthComponent,
        LoginComponent,
        RegistComponent,
        VerifyMailComponent,
        ResetPasswordComponent,
    ],
    providers: [
        AuthServicePS,
    ],
})

export class AuthModule { }

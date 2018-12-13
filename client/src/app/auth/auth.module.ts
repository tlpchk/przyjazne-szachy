import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AuthComponent} from "./auth.component";
import {AuthRoutingModule} from "./auth-routing.module";
import {AuthService} from "../_services/auth.service";
import {LoginComponent} from "./login/login.component";
import {RegistComponent} from "./regist/regist.component";
import {FormsModule} from "@angular/forms";

@NgModule({
  imports: [
    CommonModule,
    AuthRoutingModule,
    FormsModule,
  ],
  declarations: [
      AuthComponent,
      LoginComponent,
      RegistComponent
  ],
    providers:[
        AuthService
    ]
})
export class AuthModule { }

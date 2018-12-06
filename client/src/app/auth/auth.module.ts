import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LoginComponent} from "./login.component";
import {LoginRoutingModule} from "./login-routing.module";
import { AdminComponent } from './admin/admin.component';

@NgModule({
  imports: [
    CommonModule,
    LoginRoutingModule
  ],
  declarations: [
      LoginComponent,
      AdminComponent
  ]
})
export class LoginModule { }

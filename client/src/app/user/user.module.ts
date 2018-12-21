import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {BoardComponent} from "./board/board.component";
import {MessagesComponent} from "./messages/messages.component";
import {HomeComponent} from "./home/home.component";
import {SidebarComponent} from "./sidebar/sidebar.component";
import {UserComponent} from "./user.component";
import {UserRoutingModule} from "./user-routing.module";
import {AuthGuard} from "./auth.guard";

@NgModule({
    imports: [
        CommonModule,
        UserRoutingModule,


    ],
    declarations: [
        UserComponent,
        BoardComponent,
        MessagesComponent,
        HomeComponent,
        SidebarComponent,
    ],
    providers: [AuthGuard]
})

export class UserModule { }

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {InMemoryDataService} from "../_services/in-memory-data.service";
import {BoardComponent} from "./board/board.component";
import {MessagesComponent} from "./messages/messages.component";
import {HomeComponent} from "./home/home.component";
import {SidebarComponent} from "./sidebar/sidebar.component";
import {UserComponent} from "./user.component";
import {UserRoutingModule} from "./user-routing.module";
// import {HttpClientInMemoryWebApiModule} from 'angular-in-memory-web-api/http-client-in-memory-web-api.module';

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
    ]
})

export class UserModule { }

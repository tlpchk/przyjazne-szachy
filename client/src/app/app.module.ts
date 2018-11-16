import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {AppRoutingModule} from './app-routing.module';
import { UserComponent } from './user/user.component';
import {SidebarComponent} from "./user/sidebar/sidebar.component";
import {MessagesComponent} from "./user/messages/messages.component";
import {BoardComponent} from "./user/board/board.component";
import {HomeComponent} from "./user/home/home.component";
import {FormsModule} from "@angular/forms";
import {UserRoutingModule} from "./user/user-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {InMemoryDataService} from "./_services/in-memory-data.service";
import {HttpClientInMemoryWebApiModule} from 'angular-in-memory-web-api/http-client-in-memory-web-api.module';
import {UserModule} from "./user/user.module";


@NgModule({
  imports: [
      BrowserModule,
      FormsModule,
      AppRoutingModule,
      UserModule,


  ],
    declarations: [
        AppComponent,
    ],
    bootstrap: [ AppComponent ]
})
export class AppModule { }

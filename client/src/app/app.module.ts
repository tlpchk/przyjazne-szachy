import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {FormsModule} from "@angular/forms";
import {UserModule} from "./user/user.module";
import {HttpClientModule} from "@angular/common/http";
import {InMemoryDataService} from "./_services/in-memory-data.service";
import {HttpClientInMemoryWebApiModule} from 'angular-in-memory-web-api';
import {LoginModule} from "./login/login.module";
import { LoginComponent } from './login/login.component';


@NgModule({
  imports: [
      BrowserModule,
      FormsModule,
      AppRoutingModule,
      UserModule,
      LoginModule,
      HttpClientModule,

      // The HttpClientInMemoryWebApiModule module intercepts HTTP requests
      // and returns simulated server responses.
      // Remove it when a real server is ready to receive requests.
      // HttpClientInMemoryWebApiModule.forRoot(
      //     InMemoryDataService, { dataEncapsulation: false }
      // )

  ],
    declarations: [
        AppComponent,
    ],
    bootstrap: [ AppComponent ]
})
export class AppModule { }

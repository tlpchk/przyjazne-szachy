import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {FormsModule} from "@angular/forms";
import {UserModule} from "./user/user.module";
import {HttpClientModule} from "@angular/common/http";
import {InMemoryDataService} from "./_services/in-memory-data.service";
import {HttpClientInMemoryWebApiModule} from 'angular-in-memory-web-api';
import {AuthModule} from "./auth/auth.module";


@NgModule({
  imports: [
      BrowserModule,
      FormsModule,
      AppRoutingModule,
      UserModule,
      AuthModule,
      HttpClientModule,

      // The HttpClientInMemoryWebApiModule module intercepts HTTP requests
      // and returns simulated server responses.
      // Remove it when a real server is ready to receive requests.
      //  HttpClientInMemoryWebApiModule.forRoot(
      //      InMemoryDataService, { dataEncapsulation: false }
      //  )

  ],
    declarations: [
        AppComponent,
    ],
    bootstrap: [ AppComponent ]
})
export class AppModule { }

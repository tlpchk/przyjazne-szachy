import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {FormsModule} from '@angular/forms';
import {UserModule} from './user/user.module';
import {HttpClientModule} from '@angular/common/http';
import {HttpClientInMemoryWebApiModule} from 'angular-in-memory-web-api';
import {AuthModule} from './auth/auth.module';


@NgModule({
  imports: [
      BrowserModule,
      FormsModule,
      AppRoutingModule,
      UserModule,
      AuthModule,
      HttpClientModule,

  ],
    declarations: [
        AppComponent,
    ],
    bootstrap: [ AppComponent ]
})
export class AppModule { }

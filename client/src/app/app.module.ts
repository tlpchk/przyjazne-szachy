import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BoardComponent } from './board/board.component'
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {AppRoutingModule} from './app-routing.module';
import {MessagesComponent} from './messages/messages.component';
import {StartComponent} from "./start/start.component";

@NgModule({
  imports: [
      BrowserModule,
      FormsModule,
      AppRoutingModule,
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
        BoardComponent,
        MessagesComponent,
        StartComponent
    ],
    bootstrap: [ AppComponent ]
})
export class AppModule { }

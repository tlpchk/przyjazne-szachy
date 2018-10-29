import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component'
import { PieceComponent } from './piece/piece.component';
import { BoardComponent } from './board/board.component'

@NgModule({
  declarations: [
    AppComponent,
    PieceComponent,
    BoardComponent,
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

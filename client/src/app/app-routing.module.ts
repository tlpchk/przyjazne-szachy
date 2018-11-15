import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BoardComponent} from './board/board.component';
import {StartComponent} from "./start/start.component";

const routes: Routes = [
    { path: 'board', component: BoardComponent },
    { path: 'start', component: StartComponent },
    { path: '', redirectTo: '/start', pathMatch: 'full'}

];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule { }

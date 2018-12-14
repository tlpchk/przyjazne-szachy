import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BoardComponent} from "./board/board.component";
import {HomeComponent} from "./home/home.component";
import {UserComponent} from "./user.component";
import {AuthGuard} from "./auth.guard";


const routes: Routes = [
    {
        path: '', component: UserComponent,
        children: [
            {
                path: '',
                redirectTo: '/home',
                pathMatch: 'full'},
            {
                path: 'home',
                component: HomeComponent,
                canActivate: [AuthGuard]},
            {
                path: 'board',
                component: BoardComponent,
                canActivate: [AuthGuard]
            },
        ]
    },

];

@NgModule({
    imports: [ RouterModule.forChild(routes) ],
    exports: [ RouterModule ]
})

export class UserRoutingModule { }

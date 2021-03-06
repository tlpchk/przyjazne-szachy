import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {BoardComponent} from "./board/board.component";
import {HomeComponent} from "./home/home.component";
import {SidebarComponent} from "./sidebar/sidebar.component";
import {UserComponent} from "./user.component";
import {UserRoutingModule} from "./user-routing.module";
import {AuthGuard} from "./auth.guard";
import {TimerComponent} from "./timer/timer.component";
import { PopupComponent } from './popup/popup.component';
import { ProfileComponent } from './profile/profile.component';
import { RankingComponent } from './ranking/ranking.component';


@NgModule({
    imports: [
        CommonModule,
        UserRoutingModule,


    ],
    declarations: [
        UserComponent,
        BoardComponent,
        HomeComponent,
        SidebarComponent,
        TimerComponent,
        PopupComponent,
        ProfileComponent,
        RankingComponent,
    ],
    providers: [AuthGuard]
})

export class UserModule { }

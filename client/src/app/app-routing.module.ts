import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserModule} from "./user/user.module";
import {AuthModule} from "./auth/auth.module";


const routes: Routes = [
    {
        path: '',
        redirectTo: '/user',
        pathMatch: 'full'
    },
];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule { }

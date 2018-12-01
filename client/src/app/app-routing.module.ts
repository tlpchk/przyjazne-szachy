import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserModule} from "./user/user.module";
import {LoginModule} from "./login/login.module";


const routes: Routes = [
    {   path: 'user',
        loadChildren: () => UserModule,
       //loadChildren: './user/user.module.#UserModule'
    },
    {   path: 'login',
        loadChildren: () => LoginModule,
    },
    {
        path: '',
        redirectTo: 'login',
        pathMatch: 'full'
    }

];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule { }

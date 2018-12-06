import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
    {   path: 'user',
        loadChildren: './user/user.module.#UserModule'
    },
    {   path: 'auth',
        loadChildren: './user/user.module.#AuthModule'
    },
    {
        path: '',
        redirectTo: 'auth',
        pathMatch: 'full'
    }

];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule { }

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {FormsModule} from '@angular/forms';
import {UserModule} from './user/user.module';
import {HttpClientModule} from '@angular/common/http';
import { SocialLoginModule, AuthServiceConfig } from 'angularx-social-login';
import {AuthModule} from './auth/auth.module';
import {getAuthServiceConfigs} from '../socialloginConfig';

/** App module: moduł główny*/
@NgModule({
    imports: [
      BrowserModule,
      FormsModule,
      AppRoutingModule,
      UserModule,
      AuthModule,
      HttpClientModule,
      SocialLoginModule
    ],
    declarations: [
        AppComponent,
    ],
    providers: [
        {
            provide: AuthServiceConfig,
            useFactory: getAuthServiceConfigs
        }
    ],
    bootstrap: [ AppComponent ],
})
export class AppModule { }

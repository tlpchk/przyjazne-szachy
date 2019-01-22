import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {FormsModule} from '@angular/forms';
import {UserModule} from './user/user.module';
import {HttpClientModule} from '@angular/common/http';
import { SocialLoginModule, AuthServiceConfig } from 'angularx-social-login';
import { GoogleLoginProvider} from 'angularx-social-login';
import {AuthModule} from './auth/auth.module';
import {getAuthServiceConfigs} from '../socialloginConfig';

let config = new AuthServiceConfig([
    {
        id: GoogleLoginProvider.PROVIDER_ID,
        provider: new GoogleLoginProvider('809476206182-9ailps1to4r9jb0jr2ldjicpkknqa0lo.apps.googleusercontent.com')
    },
]);

export function provideConfig() {
    return config;
}
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

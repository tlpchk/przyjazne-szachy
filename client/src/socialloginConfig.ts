import { SocialLoginModule, AuthServiceConfig, GoogleLoginProvider } from "angularx-social-login";

export function getAuthServiceConfigs() {
    let config = new AuthServiceConfig([{
        id: GoogleLoginProvider.PROVIDER_ID,
        provider: new GoogleLoginProvider('809476206182-9ailps1to4r9jb0jr2ldjicpkknqa0lo.apps.googleusercontent.com')
    }]);

    return config;
}
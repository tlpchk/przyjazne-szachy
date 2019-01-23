import {Component} from '@angular/core';
import {AuthServicePS, User} from '../../_services/auth-service-p-s.service';
import {Router} from '@angular/router';
import {AuthService, GoogleLoginProvider} from 'angularx-social-login';
import {HttpClient} from '@angular/common/http';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['../auth.component.scss'],
})
export class LoginComponent{

    username = '';
    password = '';
    error = '';

    constructor(private auth: AuthServicePS,
                private authSocial: AuthService,
                private router: Router,
                private http: HttpClient) {
    }

    loginUser() {
        this.auth.logInUser(this.username, this.password).subscribe(data => {
                console.log(this.username, this.password);
                if (data.success) {
                    this.auth.currentUser = new User(this.username, this.password);
                    this.auth.setLoggedIn(true);
                    this.router.navigateByUrl('/user/home');
                } else {
                    this.error = data.message;
                    window.alert(data.message);
                }
            }
        );
    }

    public signinWithGoogle () {
        const socialPlatformProvider = GoogleLoginProvider.PROVIDER_ID;
        console.log('sing in');
        this.authSocial.signIn(socialPlatformProvider).then(
            (userData) => {
                console.log(userData);
                this.sendToRestApiMethod(userData.idToken);
            }
        );
    }
    sendToRestApiMethod(token: string): void {
        this.http.post('http://localhost:8080/googlesingin', { token: token }).subscribe(onSuccess => {
        console.log('You are logged in with google'); // login was successful save
                            // the token that you got from your REST
                            // API in your preferred location i.e. as a Cookie or LocalStorage as you do with normal login
        }, onFail => {
        console.log('You aren\'t logged in with google');
        // login was unsuccessful show an error message
        }
    );
    }
    noName() {
        this.username = 'noname';
        this.password = 'noname';
        this.loginUser();
    }
}

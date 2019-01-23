import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http'
import {Observable, of} from "rxjs";
import {AuthService, GoogleLoginProvider} from 'angularx-social-login';
import {SocialUser} from 'angularx-social-login';


const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};


interface myData {
    success: boolean;
    message: string;
}

interface UserDetails {
    username: string;
    games: number;
    wonGames: number;
    lostGames: number;
    drawGames: number;
}

class User {
    username: string;
    password: string;
}

@Injectable()
export class AuthServicePS {
    public username = "";
    public password = "";
    private loginUrl = 'http://localhost:8080/login';
    private logoutUrl = 'http://localhost:8080/logut';
    private registerUrl = 'http://localhost:8080/register';
    private loggedInStatus = false;
    private profileUrl = 'http://localhost:8080/user';

    constructor(private http: HttpClient) {
    }

    setLoggedIn(value: boolean) {
        this.loggedInStatus = value;
    }

    get isLoggedIn() {
        return this.loggedInStatus;
    }

    logOutUser(){
        this.http.post<Object>(this.logoutUrl,this.username,httpOptions);
    }


    logInUser(username, password): Observable<myData> {
        let user = new User();
        user.username = username;
        user.password = password;
        return this.http.post<myData>(this.loginUrl, user, httpOptions);
    }

    registerUser(username: string, email: string, password: string, passwordConfirmation: string): Observable<myData> {
        let newUser = new User();
        newUser.username = username;
        newUser.password = password;
        if (password != passwordConfirmation) {
            return of(<myData>{
                success: false,
                message: "Confirm password error"
            })
        }
        else {
            return this.http.post<myData>(this.registerUrl, newUser, httpOptions);
        }
    }

    getUserData(): Observable<UserDetails> {
        return this.http.post<UserDetails>(this.profileUrl, this.username, httpOptions);
    }
}
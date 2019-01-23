import {Injectable, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http'
import {Observable, of} from "rxjs";
import {AuthService, GoogleLoginProvider} from 'angularx-social-login';
import {SocialUser} from 'angularx-social-login';


const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};


interface authResponse {
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

export class User {
    username: string;
    password: string;
    email: string;

    constructor(username: string, password: string, email: string = ""){
        this.username = username;
        this.password = password;
        this.email = email;
    }
}

const loginUrl = 'http://localhost:8080/login';
const registerUrl = 'http://localhost:8080/register';
const logoutUrl = 'http://localhost:8080/bye';
const profileUrl = 'http://localhost:8080/user';

@Injectable()
export class AuthServicePS implements OnInit{
    public currentUser;
    private loggedInStatus : boolean;

    ngOnInit(): void {
         this.loggedInStatus = false;
    }

    constructor(private http: HttpClient) {
    }

    setLoggedIn(value: boolean) {
        this.loggedInStatus = value;
    }

    get isLoggedIn() {
        return this.loggedInStatus;
    }

    logInUser(username, password): Observable<authResponse> {
        this.currentUser = new User(username,password);
        return this.http.post<authResponse>(loginUrl, this.currentUser, httpOptions);
    }

    logOutUser(): Observable<Object> {
        console.log("BYE");
        this.setLoggedIn(false);
        return this.http.post<Object>(logoutUrl, this.currentUser.username, httpOptions);
    }

    validateEmail(email: string): boolean {
        let re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(String(email).toLowerCase());
    }

    registerUser(username: string, email: string, password: string, passwordConfirmation: string): Observable<authResponse> {
        this.currentUser = new User(username, password, email);
        if (password != passwordConfirmation) {
            return of(<authResponse>{
                success: false,
                message: "Confirm password error"
            })
        } else if (!this.validateEmail(email)) {
            return of(<authResponse>{
                success: false,
                message: "False email"
            })
        } else {
            return this.http.post<authResponse>(registerUrl, this.currentUser, httpOptions);
        }
    }

    getUserData(): Observable<UserDetails> {
        return this.http.post<UserDetails>(profileUrl, this.currentUser.username, httpOptions);
    }
}
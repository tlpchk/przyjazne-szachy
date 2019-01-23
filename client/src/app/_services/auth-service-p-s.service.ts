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
}

export class User {
    username: string;
    password: string;

    constructor(username: string, password: string){
        this.username = username;
        this.password = password;
    }
}

const loginUrl = 'http://localhost:8080/login';
const registerUrl = 'http://localhost:8080/register';

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

    registerUser(username: string, email: string, password: string, passwordConfirmation: string): Observable<authResponse> {
        this.currentUser = new User(username,password);
        if (password != passwordConfirmation) {
            return of(<authResponse>{
                success: false,
                message: "Confirm password error"
            })
        }
        else {
            return this.http.post<authResponse>(registerUrl, this.currentUser, httpOptions);
        }
    }
    getUserData(): Observable<UserDetails> {
        // return this.http.post<User>(this.profileUrl, httpOptions);
        return of(<UserDetails>{
            username: this.currentUser.username,
            games: 10,
            wonGames: 10,
            lostGames: 0,
        });
    }

}
import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http'
import {Observable, of} from "rxjs";

const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};


interface myData {
    success: boolean,
    message: string
}

class User {
    username: string;
    password: string;
}

@Injectable()
export class AuthService {
    private host = 'http://localhost:8080';
    private loginUrl = this.host + '/login';
    private registerUrl = this.host + '/register';
    private loggedInStatus = false;

    constructor(private http: HttpClient) {
    }

    setLoggedIn(value: boolean) {
        this.loggedInStatus = value
    }

    get isLoggedIn() {
        return this.loggedInStatus
    }

    logInUser(username, password): Observable<myData> {
        let user = new User();
        user.username = username;
        user.password = password;
        return this.http.post<myData>(this.loginUrl, user ,httpOptions);
    }

    registerUser(username: string, email: string, password1: string, password2: string): Observable<myData>{
        let newUser = new User();
        if(password1 != password2){
            return of(<myData>{
                success: false,
                message: "Confirm password error"
            })
        }
        else{
            return this.http.post<myData>(this.registerUrl, newUser, httpOptions);
        }
    }
}
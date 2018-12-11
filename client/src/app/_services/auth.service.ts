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
    private host = 'http://localhost:8080/login';
    private loggedInStatus = false;

    constructor(private http: HttpClient) {
    }

    setLoggedIn(value: boolean) {
        this.loggedInStatus = value
    }

    get isLoggedIn() {
        return this.loggedInStatus
    }

    getUserDetails(username, password): Observable<myData> {
        let a = new User();
        a.username = username;
        a.password = password;
        return this.http.post<myData>(this.host, a,httpOptions);
    }

}
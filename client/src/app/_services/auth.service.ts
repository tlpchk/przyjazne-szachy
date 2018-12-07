import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import {Observable, of} from "rxjs";

interface myData {
    success: boolean,
    message: string
}

@Injectable()
export class AuthService {
    private host = 'http://localhost:8080/login';
    private loggedInStatus = false ;

    constructor(private http: HttpClient) { }

    setLoggedIn(value: boolean) {
        this.loggedInStatus = value
    }

    get isLoggedIn() {
        return this.loggedInStatus
    }

    getUserDetails(username, password):Observable<myData> {
        return of({success: true , message: "Please log in"}) ;
        //TODO: request to the server
        return this.http.post<myData>( this.host, {
            username,
            password
        });
    }

}
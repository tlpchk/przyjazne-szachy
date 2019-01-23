import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";

const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
    providedIn: 'root'
})
export class TokenService {

    private verifyUrl = 'http://localhost:8080/verify';
    private resetUrl = 'http://localhost:8080/reset';

    constructor(private http: HttpClient) {
    }

    verifyUser(token: string): Observable<boolean> {
        let params = new HttpParams().set('token', token);
        return this.http.get<boolean>(this.verifyUrl, {params});
    }

    checkResetToken(token: string): Observable<boolean> {
        let params = new HttpParams().set('token', token);
        return this.http.get<boolean>(this.resetUrl, {params});
    }

}

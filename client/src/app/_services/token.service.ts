import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable, of} from "rxjs";

const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};

class PasswordReset {
    token: string;
    password: string;
}

interface myData {
    success: boolean;
    message: string;
}

@Injectable({
    providedIn: 'root'
})
export class TokenService {

    private verifyUrl = 'http://localhost:8080/verify';
    private resetUrl = 'http://localhost:8080/reset';
    private changeUrl = 'http://localhost:8080/reset/change';

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

    changePassword(token: string, password: string, passwordConfirmation: string): Observable<myData> {
        let passReset = new PasswordReset();
        passReset.token = token;
        passReset.password = password;
        if (password != passwordConfirmation) {
            return of(<myData>{
                success: false,
                message: "Confirm password error"
            })
        }
        else {
            return this.http.post<myData>(this.changeUrl, passReset, httpOptions);
        }
    }

}

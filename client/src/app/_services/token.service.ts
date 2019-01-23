import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {authResponse, changeUrl, httpOptions, PasswordReset, resetUrl, verifyUrl} from "./httpConection";


/** Serwis, śłużący do weryfikacji użytkownika*/
@Injectable({
    providedIn: 'root'
})
export class TokenService {

    /** @ignore*/
    constructor(private http: HttpClient) {
    }

    /** Weryfikować użytkownika
     *@param token token weryfikacji
     */
    verifyUser(token: string): Observable<boolean> {
        let params = new HttpParams().set('token', token);
        return this.http.get<boolean>(verifyUrl, {params});
    }

    /** Resetowanie
     * @param token token weryfikacji
     */
    checkResetToken(token: string): Observable<boolean> {
        let params = new HttpParams().set('token', token);
        return this.http.get<boolean>(resetUrl, {params});
    }

    /** Zmiana hasła*/
    changePassword(token: string, password: string, passwordConfirmation: string): Observable<authResponse> {
        let passReset = new PasswordReset();
        passReset.token = token;
        passReset.password = password;
        if (password != passwordConfirmation) {
            return of(<authResponse>{
                success: false,
                message: "Confirm password error"
            })
        }
        else {
            return this.http.post<authResponse>(changeUrl, passReset, httpOptions);
        }
    }

    /** Wysyłanie potwierdzenie zmiany hasła na maila*/
    sendResetMail(email: string): Observable<boolean> {
        return this.http.post<boolean>(resetUrl, email, httpOptions);
    }
}

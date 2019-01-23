import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {resetUrl, verifyUrl} from "./httpConection";


/** Serwis, śłużący do weryfikacji użytkownika*/
@Injectable({
    providedIn: 'root'
})
export class TokenService {
    /** @ignore*/
    constructor(private http: HttpClient) {}

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
        return this.http.get<boolean>( resetUrl, {params});
    }
}

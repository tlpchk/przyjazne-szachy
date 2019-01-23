import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http'
import {Observable, of} from "rxjs";
import {
    authResponse,
    httpOptions,
    loginUrl,
    logoutUrl,
    profileUrl,
    registerUrl,
    User,
    UserDetails
} from "./httpConection";

/** Serwis służący do logowania i rejestracji*/
@Injectable()
export class AuthServicePS implements OnInit{
    /** Obecnie zalogowany użytkownik*/
    public currentUser: User;
    /** Status zalogowania*/
    private loggedInStatus : boolean;

    /** Ustawia początkowy status logowania*/
    ngOnInit(): void {
         this.loggedInStatus = false;
    }

    /** @ignore*/
    constructor(private http: HttpClient) {
    }

    /**
     * Ustawia status zalogowania
     * @param value status zalogowania
     */
    setLoggedIn(value: boolean) {
        this.loggedInStatus = value;
    }

    /**
     * Przesyła dane logowania do serwera
     * @param username username użytkownika
     * @param password hasło użytkownika
     */
    logInUser(username, password): Observable<authResponse> {
        this.currentUser = new User(username,password);
        return this.http.post<authResponse>(loginUrl, this.currentUser, httpOptions);
    }

    /**
     * Procedura wylogowania się
     */
    logOutUser(): Observable<Object> {
        this.setLoggedIn(false);
        return this.http.post<Object>(logoutUrl, this.currentUser.username, httpOptions);
    }

    /**
     * Walidacja adresu skrzyńki pocztowej
     * @param email skrzyńka pocztowa
     */
    validateEmail(email: string): boolean {
        const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(String(email).toLowerCase());
    }

    /**
     * Przesyła dane rejestracji do serwera
     * @param username nazwa użytkownika
     * @param email skrzyńka pocztowa
     * @param password hasło
     * @param passwordConfirmation hasło weryfikacyjne
     */
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

    /**
     * Pobiera dane o użytkownku z serweru
     */
    getUserData(): Observable<UserDetails> {
        return this.http.post<UserDetails>(profileUrl, this.currentUser.username, httpOptions);
    }

    get isLoggedIn() {
        return this.loggedInStatus;
    }
}
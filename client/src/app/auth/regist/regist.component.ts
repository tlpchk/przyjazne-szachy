import {Component, OnInit} from '@angular/core';
import {AuthServicePS,} from "../../_services/auth-service-p-s.service";
import {Router} from "@angular/router";
import {User} from "../../_services/httpConection";

/** Komponent do wyświetlania strony rejestracji użytkownika*/
@Component({
    selector: 'app-regist',
    templateUrl: './regist.component.html',
    styleUrls: ['../auth.component.scss']
})
export class RegistComponent {
    /** Pole dla nazwy użytkownika*/
    username: string;
    /** Pole dla skrzyńki pocztowej*/
    email: string;
    /** Pole dla hasła*/
    password: string;
    /** Pole dla potwierdzenia hasła*/
    passwordConfirmation: string;

    /** @ignore*/
    constructor(private auth: AuthServicePS,
                private router: Router) {
    }

    /** Wysyła do serwisa informację o rejestracji*/
    registerUser() {
        this.auth.registerUser(this.username, this.email, this.password, this.passwordConfirmation).subscribe(data => {
            if (data.success) {
                this.auth.currentUser = new User(this.username,this.password);
                this.auth.setLoggedIn(true);
                // this.router.navigate(['/user/home']);
                this.router.navigate(['/auth/login']);
            } else {
                window.alert(data.message)
            }
        })
    }
}


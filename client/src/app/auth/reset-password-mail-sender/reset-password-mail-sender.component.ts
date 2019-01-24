import {Component, OnInit} from '@angular/core';
import {TokenService} from "../../_services/token.service";
import {Router} from "@angular/router";

/** Komponent wyświetlający okno do wpisania emailu dla resetowania hasła*/
@Component({
    selector: 'app-reset-password-mail-sender',
    templateUrl: './reset-password-mail-sender.component.html',
    styleUrls: ['../auth.component.scss']
})
export class ResetPasswordMailSenderComponent {
    /** Skrzyńka pocztowa*/
    email: string;

    /** @ignore*/
    constructor(private tokenService: TokenService,
                private router: Router) {
    }

    /** Wysyłanie nowego hasła na podany email*/
    sendResetMail() {
        this.tokenService.sendResetMail(this.email).subscribe(success => {
            if (success) {
                this.router.navigate(['/auth/login']);
            } else {
                window.alert("Mail nieprawidłowy");
            }
        });
    }
}

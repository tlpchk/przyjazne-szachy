import {Component, OnInit} from '@angular/core';
import {TokenService} from "../../_services/token.service";
import {Router} from "@angular/router";

@Component({
    selector: 'app-reset-password-mail-sender',
    templateUrl: './reset-password-mail-sender.component.html',
    styleUrls: ['./reset-password-mail-sender.component.css']
})
export class ResetPasswordMailSenderComponent implements OnInit {

    email: string;

    constructor(private tokenService: TokenService,
                private router: Router) {
    }

    ngOnInit() {
    }

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

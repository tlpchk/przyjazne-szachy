import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {TokenService} from "../../_services/token.service";

/** Umożliwia wyświetlanie okna resetowania hasła użytkownika*/
@Component({
    selector: 'app-reset-password',
    templateUrl: './reset-password.component.html',
    styleUrls: ['../auth.component.scss']
})
export class ResetPasswordComponent implements OnInit {
    /** Pole dla hasła*/
    password: string;
    /** Pole dla potwierdzenia hasła*/
    passwordConfirmation: string;
    /** Unikalny token*/
    token: string;
    /** Status zmiany*/
    success: boolean;

    /** @ignore*/
    constructor(private activatedRoute: ActivatedRoute,
                private tokenService: TokenService,
                private router: Router) {
    }

    /** @ignore*/
    ngOnInit() {
        this.activatedRoute.queryParams.subscribe(params => {
            this.token = params['token'];
            this.tokenService.checkResetToken(this.token).subscribe(success => {
                this.success=success
            })
        });
    }

    /** Resetowanie hasła*/
    resetPassword() {
        this.tokenService.changePassword(this.token,this.password,this.passwordConfirmation).subscribe(data=>{
            if (data.success) {
                this.router.navigate(['/auth/login']);
            } else {
                window.alert(data.message)
            }
        });
    }
}

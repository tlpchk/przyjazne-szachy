import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {TokenService} from "../../_services/token.service";

@Component({
    selector: 'app-reset-password',
    templateUrl: './reset-password.component.html',
    styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
    password: string;
    passwordConfirmation: string;
    token: string;
    success: boolean;

    constructor(private activatedRoute: ActivatedRoute,
                private tokenService: TokenService,
                private router: Router) {
    }

    ngOnInit() {
        this.activatedRoute.queryParams.subscribe(params => {
            this.token = params['token'];
            this.tokenService.checkResetToken(this.token).subscribe(success => {
                this.success=success
            })
        });
    }

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

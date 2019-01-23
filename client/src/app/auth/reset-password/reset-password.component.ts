import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {TokenService} from "../../_services/token.service";

@Component({
    selector: 'app-reset-password',
    templateUrl: './reset-password.component.html',
    styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
    username: string;
    password: string;
    passwordConfirmation: string;
    token: string;
    success: boolean;

    constructor(private activatedRoute: ActivatedRoute,
                private tokenService: TokenService) {
    }

    ngOnInit() {
        this.activatedRoute.queryParams.subscribe(params => {
            this.token = params['token'];
            this.tokenService.verifyUser(this.token).subscribe(success => {
                this.success=success
            })
        });
    }

    resetPassword(){}

}

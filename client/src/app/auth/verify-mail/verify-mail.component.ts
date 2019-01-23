import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {TokenService} from "../../_services/token.service";

@Component({
    selector: 'app-verify-mail',
    templateUrl: './verify-mail.component.html',
    styleUrls: ['./verify-mail.component.css']
})
export class VerifyMailComponent implements OnInit {

    token: string;
    text: string;

    constructor(private activatedRoute: ActivatedRoute,
                private tokenService: TokenService) {
    }


    ngOnInit() {
        this.activatedRoute.queryParams.subscribe(params => {
            this.token = params['token'];
            this.tokenService.verifyUser(this.token).subscribe(success => {
                if (success) {
                    this.text = "Pomyślnie uwierzytelniono";
                } else {
                    this.text = "Token nieprawidłowy lub wygasł";
                }
            })
        });
    }

}

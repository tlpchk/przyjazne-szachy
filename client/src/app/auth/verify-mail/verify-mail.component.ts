import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {TokenService} from "../../_services/token.service";

/** Komponent wyświetlający informację o weryfikacji emaila*/
@Component({
    selector: 'app-verify-mail',
    templateUrl: './verify-mail.component.html',
    styleUrls: ['./verify-mail.component.css']
})
export class VerifyMailComponent implements OnInit {
    /** Unikalny token*/
    token: string;
    /** Komunikat z informacjami*/
    text: string;

    /** @ignore*/
    constructor(private activatedRoute: ActivatedRoute,
                private tokenService: TokenService) {
    }

    /** @ignore*/
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

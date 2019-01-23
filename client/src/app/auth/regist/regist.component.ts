import {Component, OnInit} from '@angular/core';
import {AuthServicePS,} from "../../_services/auth-service-p-s.service";
import {Router} from "@angular/router";
import {User} from "../../_services/httpConection";

@Component({
    selector: 'app-regist',
    templateUrl: './regist.component.html',
    styleUrls: ['../auth.component.scss']
})
export class RegistComponent implements OnInit {
    username: string;
    email: string;
    password: string;
    passwordConfirmation: string;


    constructor(private auth: AuthServicePS,
                private router: Router) {
    }

    ngOnInit() {
    }

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


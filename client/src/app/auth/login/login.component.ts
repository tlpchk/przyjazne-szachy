import {Component, EventEmitter, OnInit, Output, ViewEncapsulation} from '@angular/core';
import {AuthService} from '../../_services/auth.service';
import {Router} from '@angular/router';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['../auth.component.scss'],
})
export class LoginComponent implements OnInit {

    username = '';
    password = '';
    error = '';

    constructor(private auth: AuthService,
                private router: Router) {
    }


    ngOnInit() {
    }


    loginUser() {
        this.auth.logInUser(this.username, this.password).subscribe(data => {
                console.log(this.username, this.password);
                if (data.success) {
                    this.auth.username = this.username;
                    this.auth.password = this.password;
                    this.auth.setLoggedIn(true);
                    this.router.navigateByUrl('/user/home');
                } else {
                    this.error = data.message;
                    window.alert(data.message);
                }
            }
        );
    }

}

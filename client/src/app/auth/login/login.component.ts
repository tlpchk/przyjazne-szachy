import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../_services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private authService: AuthService,
              private router: Router) { }

  ngOnInit() {
  }

  loginUser(event){
    event.preventDefault();
    const target = event.target;
    const username = target.querySelector('#username').value;
    const password = target.querySelector('#password').value;

    this.authService.getUserDetails(username,password).subscribe(data=> {
          if (data.success) {
              this.authService.setLoggedIn(true);
              this.router.navigate(['/user/home']);
          }else{
            window.alert(data.message)
          }
        }
    );
    console.log(event);
  }

}

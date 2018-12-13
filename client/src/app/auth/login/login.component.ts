import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../_services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

   username = "";
   password = "";

  constructor(private authService: AuthService,
              private router: Router) { }

  ngOnInit() {
  }

  loginUser(){
      this.authService.getUserDetails(this.username,this.password).subscribe(data=> {
        console.log(this.username,this.password);
          if (data.success) {
              this.authService.setLoggedIn(true);
              this.router.navigate(['/user/home']);
          }else{
            window.alert(data.message)
          }
        }
    );
  }

}

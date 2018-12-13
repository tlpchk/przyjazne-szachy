import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AuthService} from "../../_services/auth.service";
import {Router} from "@angular/router";

@Component({
    encapsulation: ViewEncapsulation.None,
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['../auth.component.scss']
})
export class LoginComponent implements OnInit {

   username = "";
   password = "";

  constructor(private auth: AuthService,
              private router: Router) { }

  ngOnInit() {
  }

  loginUser(){
      this.auth.logInUser(this.username,this.password).subscribe(data=> {
        console.log(this.username,this.password);
          if (data.success) {
              this.auth.setLoggedIn(true);
              this.router.navigate(['/user/home']);
          }else{
            window.alert(data.message)
          }
        }
    );
  }

}

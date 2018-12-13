import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../_services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-regist',
  templateUrl: './regist.component.html',
  styleUrls: ['../auth.component.scss']
})
export class RegistComponent implements OnInit {
  username: string;
  email: string;
  password1: string;
  password2: string;

  constructor(private auth: AuthService,
              private router: Router) { }

  ngOnInit() {
  }

  registerUser() {
    this.auth.registerUser(this.username,this.email,this.password1,this.password2).subscribe( data =>{
      if (data.success) {
        this.auth.setLoggedIn(true);
        this.router.navigate(['/user/home']);
      }else{
        window.alert(data.message)
      }
    })
  }


}


import {Component, EventEmitter, OnInit} from '@angular/core';
import {AuthServicePS} from "../_services/auth-service-p-s.service";

/** Component-container dla modułu użytkownika*/
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  /** @ignore*/
  constructor( private auth: AuthServicePS) { }

  /** Wylogowanie po zamknięciu okna*/
  ngOnInit() {
    const userComponent = this;
      window.onbeforeunload = function() {
      userComponent.auth.logOutUser().subscribe(response => {
        console.log('Loged out')
      });
    };
    window.onclose = function() {
      userComponent.auth.logOutUser().subscribe(response => {
          console.log('Loged out')
      });
    };
  }



}

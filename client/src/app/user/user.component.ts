import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {PopupComponent} from './popup/popup.component';
import {AuthServicePS} from "../_services/auth-service-p-s.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  constructor( private auth: AuthServicePS) { }

  ngOnInit() {

  }



}

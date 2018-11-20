import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs";

@Component({
  selector: 'alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css']
})
export class AlertComponent implements OnInit {

  private subscription: Subscription;
  message: any;

  constructor() { }

  ngOnInit() {
  }

}

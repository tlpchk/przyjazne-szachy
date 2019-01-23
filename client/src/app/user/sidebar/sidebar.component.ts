import { Component, OnInit } from '@angular/core';
import {MessageService} from "../../_services/message.service";
import {AuthServicePS} from "../../_services/auth-service-p-s.service";

@Component({
  selector: 'sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  constructor(private messeageService: MessageService,
              private auth: AuthServicePS) { }

  ngOnInit() {
  }

  clearMessages(){
    this.messeageService.clear();
  }

  logOut(){
    this.auth.setLoggedIn(false);
  }
}

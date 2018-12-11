import { Component, OnInit } from '@angular/core';
import {MessageService} from "../../_services/message.service";

@Component({
  selector: 'sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  constructor(private messeageService: MessageService) { }

  ngOnInit() {
  }

  clearMessages(){
    this.messeageService.clear();
  }
}

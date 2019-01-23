import {Component, OnInit} from '@angular/core';
import {AuthServicePS} from "../../_services/auth-service-p-s.service";

@Component({
    selector: 'sidebar',
    templateUrl: './sidebar.component.html',
    styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

    constructor(private authService: AuthServicePS) {
    }

    ngOnInit() {
    }

    logout() {
        this.authService.logOutUser().subscribe( response => { console.log('OK')});
    }

}

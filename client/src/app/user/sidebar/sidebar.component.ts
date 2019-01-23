import {Component, OnInit} from '@angular/core';
import {AuthServicePS} from "../../_services/auth-service-p-s.service";

@Component({
    selector: 'sidebar',
    templateUrl: './sidebar.component.html',
    styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

    isLoggedUser: boolean;

    constructor(private authService: AuthServicePS) {
    }

    ngOnInit() {
        this.isLoggedUser = (this.authService.currentUser.username != "noname");
    }

    logout() {
        this.authService.logOutUser().subscribe(response => {
            console.log('OK')
        });
    }

}

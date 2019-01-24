import {Component, OnInit} from '@angular/core';
import {AuthServicePS} from "../../_services/auth-service-p-s.service";

/** Komponent służący do wyświetlania paseku nawigacyjnego*/
@Component({
    selector: 'sidebar',
    templateUrl: './sidebar.component.html',
    styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

    /** Czy jest użytkownik jest zalogowany*/
    isLoggedUser: boolean;

    /** @ignore*/
    constructor(private authService: AuthServicePS) {}

    /** Ustawienie flagi zalogowanego użytkownika*/
    ngOnInit() {
        this.isLoggedUser = (this.authService.currentUser.username != "noname");
    }

    /** Umożliwia wylogowanie się*/
    logout() {
        this.authService.logOutUser().subscribe(response => {
            console.log('OK')
        });
    }

}

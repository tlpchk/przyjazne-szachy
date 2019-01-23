import { Component, OnInit } from '@angular/core';
import {AuthServicePS} from '../../_services/auth-service-p-s.service';
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  constructor(private auth: AuthServicePS) { }

  username: string;
  games: number;
  wonGames: number;
  lostGames: number;

  ngOnInit() {
    this.auth.getUserData().subscribe( userData => {
      this.username = userData.username;
      this.games = userData.games;
      this.wonGames = userData.wonGames;
      this.lostGames = userData.lostGames;
    });
  }

}


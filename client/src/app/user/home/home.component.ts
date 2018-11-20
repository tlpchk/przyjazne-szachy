import { Component, OnInit } from '@angular/core';
import {StartService} from "../../_services/start.service";
import {GameInfo} from "../../_models/gameInfo";

@Component({
  selector: 'app-start',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  gameList: GameInfo[];


  constructor(private startService: StartService,

  ) { }

  ngOnInit() {
    this.getGameList()
  }

  private getGameList() {
    this.startService.getGameList().subscribe(list => this.gameList = list);
  }

  createNewGame(){
    this.startService.createNewGame().subscribe(newGame => console.log(newGame.id));
  }
}

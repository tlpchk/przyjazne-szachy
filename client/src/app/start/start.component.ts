import { Component, OnInit } from '@angular/core';
import {StartService} from "../services/start.service";
import {GameInfo} from "../gameInfo";

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.scss']
})
export class StartComponent implements OnInit {

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

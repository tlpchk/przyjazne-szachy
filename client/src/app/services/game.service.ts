import { Injectable } from '@angular/core';
import {Observable, Subject} from "rxjs";
import {GameDTO} from "../gameDTO";

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private game: Subject<GameDTO> = new Subject<GameDTO>();
  constructor() { }

  setGame(game: GameDTO){
      this.game.next(game);
  }

  getGame(): Observable<any>{
      return this.game.asObservable();
  }

  clearGame(){
      this.game.next();
  }


}

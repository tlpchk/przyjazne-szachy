import { Injectable } from '@angular/core';
import {GameInfo} from "../_models/gameInfo";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class StartService {

  private gameListUrl = 'http://localhost:8080/gameEntity/list';
  private newGameUrl = 'http://localhost:8080/gameEntity/create';

  constructor(private http: HttpClient) { }


  getGameList() : Observable<GameInfo[]>{
    return this.http.get<GameInfo[]>(this.gameListUrl);
  }

    createNewGame() {
        return this.http.post<GameInfo>(this.newGameUrl, {}, httpOptions);
    }
}

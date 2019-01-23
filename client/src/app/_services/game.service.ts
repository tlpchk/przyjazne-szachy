import { Injectable } from '@angular/core';
import {Observable, Subject} from "rxjs";
import {CreateGameDTO} from '../_models/createGameDTO';
import {Color} from '../_models/color';
import {PlayerType} from '../_models/playerType';
import {CreatePlayerDTO} from '../_models/createPlayerDTO';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AuthServicePS} from './auth-service-p-s.service';

const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class GameService {

    private gamesUrl = 'http://localhost:8080/games';
    private playersUrl = 'http://localhost:8080/players';
    private joinSubUrl = "/join";

    constructor(private http: HttpClient,
                private auth: AuthServicePS) {
    }


    getGameList(): Observable<number[]> {
        return this.http.get<number[]>(this.gamesUrl);
    }

    createNewGame(firstPlayerId: number, secondPlayerId: number , isRanked: boolean): Observable<number> {
        let createGameDTO = new CreateGameDTO(firstPlayerId, secondPlayerId, isRanked);
        return this.http.post<number>(this.gamesUrl, createGameDTO, httpOptions);
    }

    createPlayer(playerColor: Color, playerType: PlayerType): Observable<number> {
        let createPlayerDTO = new CreatePlayerDTO(this.auth.currentUser.username, playerColor, playerType);
        return this.http.post<number>(this.playersUrl, createPlayerDTO, httpOptions);
    }

    joinGame(gameId: number, playerId: number): Observable<boolean> {
        let url = this.gamesUrl + "/" + gameId + this.joinSubUrl;
        return this.http.post<boolean>(url, playerId, httpOptions);
    }


}

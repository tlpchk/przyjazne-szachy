import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {CreateGameDTO} from '../DTO/createGameDTO';
import {Color} from '../_models/color';
import {PlayerType} from '../_models/playerType';
import {CreatePlayerDTO} from '../DTO/createPlayerDTO';
import {HttpClient} from '@angular/common/http';
import {AuthServicePS} from './auth-service-p-s.service';
import {gamesUrl, httpOptions, joinSubUrl, playersUrl} from "./httpConection";

/** Serwis służy do pobierania z serwera informacji o grze*/
@Injectable({
  providedIn: 'root'
})
export class GameService {

    /** @ignore*/
    constructor(private http: HttpClient,
                private auth: AuthServicePS) {
    }

    /** Pobieranie listy gier*/
    getGameList(): Observable<number[]> {
        return this.http.get<number[]>(gamesUrl);
    }

    /** Tworzenie nowej gry*/
    createNewGame(firstPlayerId: number, secondPlayerId: number , isRanked: boolean): Observable<number> {
        let createGameDTO = new CreateGameDTO(firstPlayerId, secondPlayerId, isRanked);
        return this.http.post<number>(gamesUrl, createGameDTO, httpOptions);
    }

    /** Tworzenie nowego gracza*/
    createPlayer(playerColor: Color, playerType: PlayerType): Observable<number> {
        let createPlayerDTO = new CreatePlayerDTO(this.auth.currentUser.username, playerColor, playerType);
        return this.http.post<number>(playersUrl, createPlayerDTO, httpOptions);
    }

    /** Dołączanie się do gry*/
    joinGame(gameId: number, playerId: number): Observable<boolean> {
        let url = gamesUrl + "/" + gameId + joinSubUrl;
        return this.http.post<boolean>(url, playerId, httpOptions);
    }


}

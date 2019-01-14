import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {CreateGameDTO} from "../_models/createGameDTO";

const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};


@Injectable({
    providedIn: 'root'
})

export class UserService {

    private gamesUrl = 'http://localhost:8080/games';
    private humanPlayersUrl = 'http://localhost:8080/players/humans';
    private botPlayersUrl = 'http://localhost:8080/players/bots';
    private joinSubUrl = "/join";

    constructor(private http: HttpClient) {
    }


    getGameList(): Observable<number[]> {
        return this.http.get<number[]>(this.gamesUrl);
    }

    createNewGame(firstPlayerId: number, secondPlayerId: number): Observable<number> {
        let createGameDTO = new CreateGameDTO(firstPlayerId, secondPlayerId, false);
        return this.http.post<number>(this.gamesUrl, createGameDTO, httpOptions);
    }

    createNewHumanPlayer(playerColor: String): Observable<number> {
        return this.http.post<number>(this.humanPlayersUrl, playerColor, httpOptions);
    }

    createNewBotPlayer(playerColor: String): Observable<number> {
        return this.http.post<number>(this.botPlayersUrl, playerColor, httpOptions);
    }

    joinGame(gameId: number, playerId: number): Observable<boolean> {
        let url = this.gamesUrl + "/" + gameId + this.joinSubUrl;
        return this.http.post<boolean>(url, playerId, httpOptions);
    }
}

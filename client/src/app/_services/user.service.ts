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
    private playersUrl = 'http://localhost:8080/players';
    private joinSubUrl = "/join";

    constructor(private http: HttpClient) {
    }


    getGameList(): Observable<number[]> {
        return this.http.get<number[]>(this.gamesUrl);
    }

    createNewGame(playerId: number): Observable<number> {
        let createGameDTO = new CreateGameDTO(playerId, null);
        return this.http.post<number>(this.gamesUrl, createGameDTO, httpOptions);
    }

    createNewPlayer(playerColor: String): Observable<number> {
        return this.http.post<number>(this.playersUrl, playerColor, httpOptions);
    }

    joinGame(gameId: number, playerId: number): Observable<boolean> {
        let url = this.gamesUrl + "/" + gameId + this.joinSubUrl;
        return this.http.post<boolean>(url, playerId, httpOptions);
    }
}

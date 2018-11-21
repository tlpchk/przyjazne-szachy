import {Injectable} from '@angular/core';
import {GameDTO} from "../_models/gameDTO";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {PlayerDTO} from "../_models/playerDTO";

const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};

const firstPlayerColor = "WHITE";

@Injectable({
    providedIn: 'root'
})

export class StartService {

    private gamesUrl = 'http://localhost:8080/games';
    private playersUrl = 'http://localhost:8080/players';

    constructor(private http: HttpClient) {
    }


    getGameList(): Observable<GameDTO[]> {
        return this.http.get<GameDTO[]>(this.gamesUrl);
    }

    createNewGame(player: PlayerDTO): Observable<GameDTO> {
        return this.http.post<GameDTO>(this.gamesUrl, player, httpOptions);
    }

    createNewPlayer(): Observable<PlayerDTO> {
        return this.http.post<PlayerDTO>(this.playersUrl, firstPlayerColor, httpOptions);
    }
}

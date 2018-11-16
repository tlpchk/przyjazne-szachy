import {Injectable} from '@angular/core';
import {GameDTO} from "../gameDTO";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
    providedIn: 'root'
})

export class StartService {

    private gameListUrl = 'http://localhost:8080/game/list';
    private newGameUrl = 'http://localhost:8080/game/create';
    private joinGameUrl = 'http://localhost:8080/game/join';

    constructor(private http: HttpClient) {
    }


    getGameList(): Observable<GameDTO[]> {
        return this.http.get<GameDTO[]>(this.gameListUrl);
    }

    createNewGame() {
        return this.http.post<GameDTO>(this.newGameUrl, {}, httpOptions);
    }

    joinGame(gameId: number) {
        let game = new GameDTO();
        game.id = gameId;
        return this.http.post<GameDTO>(this.joinGameUrl, game, httpOptions);
    }
}

import {Injectable} from '@angular/core';
import {Observable, of} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CreateGameDTO} from "../_models/createGameDTO";

const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
    providedIn: 'root'
})
export class TimerService {

  private timerUrl = 'api/timer';

    constructor(private http: HttpClient) {
    }

    timeLeftInSeconds: number = 30 * 60;
    playerId: number;
    gameId: number;
    updator;


    public startTimer() {
        const timerService = this;
        this.updator = setInterval(function () {
            timerService.synchronize();
            if (timerService.timeLeftInSeconds < 0) {
                timerService.clearTimer();
            }
        }, 1000);
    }

    synchronize() {
        if (this.playerId != null && this.gameId != null) {
            this.getLeftTimeFor(this.playerId, this.gameId).subscribe(seconds => {
                this.timeLeftInSeconds = seconds;
            });
        }
    }

    clearTimer() {
        this.timeLeftInSeconds = 0;
        clearInterval(this.updator);
    }

    getLeftTimeFor(playerId: number, gameId: number): Observable<number> {
        let url = this.gamesUrl + '/' + gameId + this.timerSubUrl;
        return this.http.post<number>(url, playerId, httpOptions);
    }


}

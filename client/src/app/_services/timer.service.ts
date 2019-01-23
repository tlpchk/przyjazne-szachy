import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {gamesUrl, httpOptions, timerSubUrl} from "./httpConection";


/** Serwis nadaje informację o tajmerze*/
@Injectable({
    providedIn: 'root'
})
export class TimerService {
    /** @ignore*/
    constructor(private http: HttpClient) {}

    /** Pozostały czas*/
    timeLeftInSeconds = 30 * 60;
    /** Id gracza*/
    playerId: number;
    /** Id gry*/
    gameId: number;
    /** Aktualizator*/
    updator;

    /** Zacząć liczyć czas*/
    public startTimer() {
        const timerService = this;
        this.updator = setInterval(function () {
            timerService.synchronize();
            if (timerService.timeLeftInSeconds < 0) {
                timerService.clearTimer();
            }
        }, 1000);
    }

    /** Synchronizacja*/
    synchronize() {
        if (this.playerId != null && this.gameId != null) {
            this.getLeftTimeFor(this.playerId, this.gameId).subscribe(seconds => {
                this.timeLeftInSeconds = seconds;
            });
        }
    }

    /** Oczyścić tajmer*/
    clearTimer() {
        this.timeLeftInSeconds = 0;
        clearInterval(this.updator);
    }

    /** Pobrać pozostały czas*/
    getLeftTimeFor(playerId: number, gameId: number): Observable<number> {
        let url = gamesUrl + '/' + gameId + timerSubUrl;
        return this.http.post<number>(url, playerId, httpOptions);
    }


}

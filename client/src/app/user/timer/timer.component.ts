import {Component, OnInit} from '@angular/core';
import {TimerService} from '../../_services/timer.service';

/** Komponent służący do wyświetlania pozpstałego czasu*/
@Component({
    selector: 'app-timer',
    templateUrl: './timer.component.html',
    styleUrls: ['./timer.component.scss']
})
export class TimerComponent implements OnInit {

    /** @ignore*/
    constructor(private timer: TimerService) {}

    /** Wartość tajmera w postaci string MM:SS*/
    timerValue: string;

    /** Object służący do odświeżania czasu*/
    updator;

    /** Tworzenie odświeżacza*/
    ngOnInit() {
        const timerComponent = this;
        this.updator = setInterval(function () {
            let minutes = Math.floor(timerComponent.timer.timeLeftInSeconds / 60);
            let seconds = timerComponent.timer.timeLeftInSeconds % 60;

            timerComponent.formatTimer(minutes, seconds);

            if (timerComponent.timer.timeLeftInSeconds < 0) {
                timerComponent.clearTimer();
            }
        }, 1000);


    }

    /** Formatowanie tajmeru*/
    formatTimer(minutes, seconds) {
        if (minutes < 10) {
            minutes = "0" + minutes;
        }
        if (seconds < 10) {
            seconds = "0" + seconds;
        }
        this.timerValue = minutes + ":" + seconds;
    }

    /** Zerowanie timeru*/
    clearTimer() {
        this.timerValue = "00:00";
        clearInterval(this.updator);
    }
}

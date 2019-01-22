import {Component, OnInit} from '@angular/core';
import {TimerService} from '../../_services/timer.service';

@Component({
    selector: 'app-timer',
    templateUrl: './timer.component.html',
    styleUrls: ['']
})
export class TimerComponent implements OnInit {

    constructor(private timer: TimerService) {
    }

    timerValue: string;

    updator;

    ngOnInit() {
        const timerComponent = this;
        this.updator = setInterval(function () {
            let minutes = Math.floor(timerComponent.timer.timeLeftInSeconds / 60);
            let seconds = timerComponent.timer.timeLeftInSeconds % 60;

            timerComponent.saveValue(minutes, seconds);

            if (timerComponent.timer.timeLeftInSeconds < 0) {
                timerComponent.clearTimer();
            }
        }, 1000);


    }

    saveValue(minutes, seconds) {
        if (minutes < 10) {
            minutes = "0" + minutes;
        }
        if (seconds < 10) {
            seconds = "0" + seconds;
        }
        this.timerValue = minutes + ":" + seconds;
    }

    clearTimer() {
        this.timerValue = "00:00";
        clearInterval(this.updator);
    }
}

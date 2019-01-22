import {Component, OnInit} from '@angular/core';
import {TimerService} from '../../_services/timer.service';

@Component({
    selector: 'app-timer',
    templateUrl: './timer.component.html',
    styleUrls: ['./timer.component.scss']
})
export class TimerComponent implements OnInit {

    constructor(private timer: TimerService) {
    }

    timerValue: string;

    ngOnInit() {
        let timerComponent = this;

        timerComponent.timer.getTimeOfEnd().subscribe(miliseconds => {
            let now = new Date();
            let timeOfEnd = new Date(miliseconds);
            let distance = timeOfEnd.getTime() - now.getTime()
             // seconds 2 miliseconds

            setInterval(function () {
                let minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                let seconds = Math.floor((distance % (1000 * 60)) / 1000);

                timerComponent.updateTimer(minutes, seconds);

                if (distance < 0) {
                    clearInterval(this);
                    timerComponent.timerValue = 'Over';
                }
                distance = distance - 1000;
            }, 1000);
        }) ;

    }


    updateTimer(minutes, seconds) {
        this.timerValue = minutes + ":" + seconds;
    }
}

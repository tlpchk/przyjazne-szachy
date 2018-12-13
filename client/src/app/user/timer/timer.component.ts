import {Component, OnInit} from '@angular/core';
import {TimerService} from "../../_services/timer.service";

@Component({
    selector: 'app-timer',
    templateUrl: './timer.component.html',
    styleUrls: ['']
})
export class TimerComponent implements OnInit {

    constructor(private timer: TimerService) {
    }

    timerValue: string;

    ngOnInit() {
        const timerComponent = this;
        setInterval(function () {

            let now = new Date();
            let timeOfEnd = now;
            timerComponent.timer.getTimeOfEnd(now).subscribe(date =>{
                timeOfEnd = date;
            });

            let distance = timeOfEnd.getTime() - now.getTime();


            let minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
            let seconds = Math.floor((distance % (1000 * 60)) / 1000);

            timerComponent.updateTimer(minutes ,seconds)

            if (distance < 0) {
              clearInterval(this);
              timerComponent.timerValue = "Over"
            }
        }, 1000);

    }


    updateTimer(minutes,seconds){
        this.timerValue = minutes + ":" + seconds;
    }
}

import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";

@Component({
    selector: 'app-timer',
    templateUrl: './timer.component.html',
    styleUrls: ['./timer.component.css']
})
export class TimerComponent implements OnInit {


    constructor() {
    }

    timerValue: string;

    ngOnInit() {
        this.timer();
    }

    timer() {
        let countDownDate = new Date("Dec 12, 2018 15:37:25").getTime();
        const that = this;

        // Update the count down every 1 second
        setInterval(function () {

            // Get todays date and time
            let now = new Date().getTime();

            // Find the distance between now and the count down date
            let distance = countDownDate - now;

            let minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
            let seconds = Math.floor((distance % (1000 * 60)) / 1000);

            // this.timerValue = minutes + "m " + seconds + "s ";
            // console.log(this.timerValue);

            that.updateTimer(minutes + "m " + seconds + "s ")

            // If the count down is over, write some text
            /*if (distance < 0) {
              clearInterval(x);
              document.getElementById("").innerHTML = "EXPIRED";
            }*/
        }, 1000);

    }

    updateTimer = (time: string) => this.timerValue = time;
}

import { Injectable } from '@angular/core';
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

  private timerUrl = 'http://localhost:8080/timer';

  private mockDate = new Date("Dec 12, 2018 22:12:25");

  constructor(private http: HttpClient) { }

  getTimeOfEnd(now: Date): Observable<Date>{
    //return this.http.post<Date>(this.timerUrl,now,httpOptions);
    return of(this.mockDate);
  }


}

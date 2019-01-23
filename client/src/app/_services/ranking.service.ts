import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {gamesUrl, RankingRecord, rankingUrl} from "./httpConection";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class RankingService {

  constructor(private http: HttpClient) { }

  getRanking(): Observable<RankingRecord[]> {
    return this.http.get<RankingRecord[]>(rankingUrl);
  }
}

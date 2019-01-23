import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {gamesUrl, RankingRecord, rankingUrl} from "./httpConection";
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class RankingService {

    constructor(private http: HttpClient) {
    }

    getRanking(): Observable<RankingRecord[]> {
        let params = new HttpParams().set('page', '1').set('size','50');
        return this.http.get<RankingRecord[]>(rankingUrl,{params});
    }
}

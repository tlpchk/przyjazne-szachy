import {Component, OnInit} from '@angular/core';
import {RankingRecord} from "../../_services/httpConection";
import {RankingService} from "../../_services/ranking.service";

@Component({
    selector: 'app-ranking',
    templateUrl: './ranking.component.html',
    styleUrls: ['./ranking.component.scss']
})
export class RankingComponent implements OnInit {

    loading = "Łąduję...";
    ranking: RankingRecord[]=[];

    constructor(private rankService: RankingService) {
    }

    ngOnInit() {
        this.rankService.getRanking().subscribe(ranking => {
            this.loading = "";
            this.ranking = ranking;
        })
    }

}

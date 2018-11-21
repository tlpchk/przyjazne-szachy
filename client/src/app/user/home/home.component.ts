import {Component, OnInit} from '@angular/core';
import {StartService} from "../../_services/start.service";
import {GameDTO} from "../../_models/gameDTO";
import {BoardService} from "../../_services/board.service";
import {PlayerDTO} from "../../_models/playerDTO";

@Component({
    selector: 'app-start',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

    gameList: GameDTO[];


    constructor(private startService: StartService,
                private boardService: BoardService) {
    }

    ngOnInit() {
        this.getGameList();
    }

    private getGameList() {
        this.startService.getGameList().subscribe(list => this.gameList = list);
    }

    //TODO RS: player creation should be another option
    createNewGame() {
        this.startService.createNewPlayer().subscribe(player => {
            console.log(player);
            this.startService.createNewGame(player).subscribe(game => {
                console.log(game);
                this.boardService.playerId = player.id;
                this.boardService.gameId = game.id;
                console.log("PI: "+player.id+" "+game.id);
            });
        });
    }
}

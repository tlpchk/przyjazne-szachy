import {Component, OnInit} from '@angular/core';
import {StartService} from "../../_services/start.service";
import {GameDTO} from "../../_models/gameDTO";
import {BoardService} from "../../_services/board.service";

const firstPlayerColor = "WHITE";
const secondPlayerColor = "BLACK";

@Component({
    selector: 'app-start',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

    gameList: GameDTO[];
    gameId: number;


    constructor(private startService: StartService,
                private boardService: BoardService) {
    }

    ngOnInit() {
        this.getGameList();

    }

    private getGameList() {
        this.startService.getGameList().subscribe(list => {
            this.gameList = list;
        });
    }

    //TODO RS: creating player creation should be another option
    createNewGame() {
        this.startService.createNewPlayer(firstPlayerColor).subscribe(player => {
            this.startService.createNewGame(player).subscribe(game => {
                this.boardService.playerId = player.id;
                this.boardService.gameId.next(game.id);
            });
        });
    }

    //TODO RS: creating player creation should be another option
    joinGame(gameId: number) {
        this.startService.createNewPlayer(secondPlayerColor).subscribe(player => {
            this.startService.joinGame(gameId, player).subscribe(wasSuccesfull => {
                if (wasSuccesfull) {
                    this.boardService.playerId = player.id;
                    this.boardService.gameId.next(gameId);
                } else {
                    console.log("Cannot join to game");
                }
            });
        });
    }
}

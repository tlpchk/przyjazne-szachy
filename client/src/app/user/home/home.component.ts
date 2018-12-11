import {Component, OnInit} from '@angular/core';
import {StartService} from "../../_services/start.service";
import {BoardService} from "../../_services/board.service";

const firstPlayerColor = "WHITE";
const secondPlayerColor = "BLACK";

@Component({
    selector: 'app-start',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

    gameList: number[];

    //gameId: number;


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
    createNewCompetitionGame() {
        this.startService.createNewHumanPlayer(firstPlayerColor).subscribe(playerId => {
            this.startService.createNewGame(playerId,null).subscribe(gameId => {
                this.boardService.playerId = playerId;
                this.boardService.gameId.next(gameId);
            });
        });
    }

    createNewBotGame() {
        this.startService.createNewHumanPlayer(firstPlayerColor).subscribe(playerId => {
            this.startService.createNewBotPlayer(secondPlayerColor).subscribe(botId => {
                this.startService.createNewGame(playerId,botId).subscribe(gameId => {
                    this.boardService.playerId = playerId;
                    this.boardService.gameId.next(gameId);
                });
            });
        });
    }

    //TODO RS: creating player creation should be another option
    joinGame(gameId: number) {
        this.startService.createNewHumanPlayer(secondPlayerColor).subscribe(playerId => {
            this.startService.joinGame(gameId, playerId).subscribe(wasSuccessful => {
                if (wasSuccessful) {
                    this.boardService.playerId = playerId;
                    this.boardService.gameId.next(gameId);
                } else {
                    console.log("Cannot join to game");
                }
            });
        });
    }
}

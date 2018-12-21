import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {UserService} from "../../_services/user.service";
import {BoardService} from "../../_services/board.service";

const firstPlayerColor = "WHITE";
const secondPlayerColor = "BLACK";

@Component({
    selector: 'app-start',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {

    gameList: number[];


    constructor(private userService: UserService,
                private boardService: BoardService) {
    }

    ngOnInit() {
        this.getGameList();

    }

    private getGameList() {
        this.userService.getGameList().subscribe(list => {
            this.gameList = list;
        });
    }

    //TODO RS: creating player creation should be another option
    createNewCompetitionGame() {
        this.userService.createNewHumanPlayer(firstPlayerColor).subscribe(playerId => {
            this.userService.createNewGame(playerId,null).subscribe(gameId => {
                this.boardService.playerId = playerId;
                this.boardService.gameId.next(gameId);
            });
        });
    }

    createNewBotGame() {
        this.userService.createNewHumanPlayer(firstPlayerColor).subscribe(playerId => {
            this.userService.createNewBotPlayer(secondPlayerColor).subscribe(botId => {
                this.userService.createNewGame(playerId,botId).subscribe(gameId => {
                    this.boardService.playerId = playerId;
                    this.boardService.gameId.next(gameId);
                });
            });
        });
    }

    //TODO RS: creating player creation should be another option
    joinGame(gameId: number) {
        this.userService.createNewHumanPlayer(secondPlayerColor).subscribe(playerId => {
            this.userService.joinGame(gameId, playerId).subscribe(wasSuccessful => {
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

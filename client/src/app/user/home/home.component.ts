import {Component, OnInit} from '@angular/core';
import {UserService} from "../../_services/user.service";
import {BoardService} from "../../_services/board.service";
import {Color} from "../../_models/color";
import {PlayerType} from "../../_models/playerType";

const firstPlayerColor = Color.white;
const secondPlayerColor = Color.black;

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

    createNewCompetitionGame() {
        this.userService.createPlayer(firstPlayerColor, PlayerType.human).subscribe(playerId => {
            this.userService.createNewGame(playerId, null).subscribe(gameId => {
                this.boardService.playerId = playerId;
                this.boardService.playerColor = firstPlayerColor;
                this.boardService.gameId.next(gameId);
            });
        });
    }

    createNewBotGame() {
        this.userService.createPlayer(firstPlayerColor, PlayerType.human).subscribe(playerId => {
            this.userService.createPlayer(secondPlayerColor, PlayerType.bot).subscribe(botId => {
                this.userService.createNewGame(playerId, botId).subscribe(gameId => {
                    this.boardService.playerId = playerId;
                    this.boardService.playerColor = firstPlayerColor;
                    this.boardService.gameId.next(gameId);
                });
            });
        });
    }

    joinGame(gameId: number) {
        this.userService.createPlayer(secondPlayerColor, PlayerType.human).subscribe(playerId => {
            this.userService.joinGame(gameId, playerId).subscribe(wasSuccessful => {
                if (wasSuccessful) {
                    this.boardService.playerId = playerId;
                    this.boardService.playerColor = secondPlayerColor;
                    this.boardService.gameId.next(gameId);
                } else {
                    console.log("Cannot join to game");
                }
            });
        });
    }
}

import {Color} from '../../_models/color';
import {Component, OnInit} from '@angular/core';
import {GameService} from '../../_services/game.service';
import {BoardService} from '../../_services/board.service';
import {PlayerType} from '../../_models/playerType';


const firstPlayerColor = Color.white;
const secondPlayerColor = Color.black;

@Component({
    selector: 'app-start',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {

    gameList: number[];


    constructor(private gameService: GameService,
                private boardService: BoardService) {
    }

    ngOnInit() {
        this.getGameList();
    }

    getGameList() {
        this.gameService.getGameList().subscribe(list => {
            this.gameList = list;
        });
    }

    createNewCompetitionGame() {
        this.gameService.createPlayer(firstPlayerColor, PlayerType.human).subscribe(playerId => {
            this.gameService.createNewGame(playerId, null).subscribe(gameId => {
                this.boardService.playerId = playerId;
                this.boardService.playerColor = firstPlayerColor;
                this.boardService.gameId.next(gameId);
            });
        });
    }

    createNewBotGame() {
        this.gameService.createPlayer(firstPlayerColor, PlayerType.human).subscribe(playerId => {
            this.gameService.createPlayer(secondPlayerColor, PlayerType.bot).subscribe(botId => {
                this.gameService.createNewGame(playerId, botId).subscribe(gameId => {
                    this.boardService.playerId = playerId;
                    this.boardService.playerColor = firstPlayerColor;
                    this.boardService.gameId.next(gameId);
                });
            });
        });
    }

    joinGame(gameId: number) {
        this.gameService.createPlayer(secondPlayerColor, PlayerType.human).subscribe(playerId => {
            this.gameService.joinGame(gameId, playerId).subscribe(wasSuccessful => {
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

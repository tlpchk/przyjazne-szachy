import {Color} from '../../_models/color';
import {Component, OnInit} from '@angular/core';
import {GameService} from '../../_services/game.service';
import {BoardService} from '../../_services/board.service';
import {PlayerType} from '../../_models/playerType';
import {Observable} from "rxjs";
import {CreateGameDTO} from "../../DTO/createGameDTO";


const firstPlayerColor = Color.white;
const secondPlayerColor = Color.black;

@Component({
    selector: 'app-start',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
    isRanked: boolean;
    botGame: boolean;
    gameList: number[];

    constructor(private gameService: GameService,
                private boardService: BoardService) {
    }

    ngOnInit() {
        this.gameList = [];
        const homeComponent = this;
        setInterval(function () {
            homeComponent.getGameList()
        },1000);
    }

    getGameList() {
        this.gameService.getGameList().subscribe(list => {
            this.gameList = list;
        });
    }


    createNewCompetitionGame(isRanked:boolean) {
        this.gameService.createPlayer(firstPlayerColor, PlayerType.human).subscribe(playerId => {
            this.gameService.createNewGame(playerId, null, isRanked).subscribe(gameId => {
                this.boardService.playerId = playerId;
                this.boardService.playerColor = firstPlayerColor;
                this.boardService.gameId.next(gameId);
            });
        });
    }

    createNewBotGame(isRanked:boolean) {
        this.gameService.createPlayer(firstPlayerColor, PlayerType.human).subscribe(playerId => {
            this.gameService.createPlayer(secondPlayerColor, PlayerType.bot).subscribe(botId => {
                this.gameService.createNewGame(playerId, botId,isRanked).subscribe(gameId => {
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

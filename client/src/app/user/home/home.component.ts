import {Color} from '../../_models/color';
import {Component, OnInit} from '@angular/core';
import {GameService} from '../../_services/game.service';
import {BoardService} from '../../_services/board.service';
import {PlayerType} from '../../_models/playerType';

/** Kolor pierwszego gracza*/
const firstPlayerColor = Color.white;
/** Kolor drugiego gracza*/
const secondPlayerColor = Color.black;


/** Komponent służący do wyświetlania strony głównej użytkownika*/
@Component({
    selector: 'app-start',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
    /** Flaga czy nowa gra będzie rankingowa*/
    isRanked: boolean;
    /** Flaga czy nowa gra będzie z botem*/
    botGame: boolean;
    /** Lista dostępnych gier*/
    gameList: number[];

    /** @ignore*/
    constructor(private gameService: GameService,
                private boardService: BoardService) {
    }

    /** @ignore*/
    ngOnInit() {
        this.gameList = [];
        const homeComponent = this;
        setInterval(function () {
            homeComponent.getGameList()
        },1000);
    }

    /** Pobieranie listy dostępnych gier*/
    getGameList() {
        this.gameService.getGameList().subscribe(list => {
            this.gameList = list;
        });
    }

    /** Stworzyć nową grę z człowiekiem*/
    createNewCompetitionGame(isRanked:boolean) {
        this.gameService.createPlayer(firstPlayerColor, PlayerType.human).subscribe(playerId => {
            this.gameService.createNewGame(playerId, null, isRanked).subscribe(gameId => {
                this.boardService.playerId = playerId;
                this.boardService.playerColor = firstPlayerColor;
                this.boardService.gameId.next(gameId);
            });
        });
    }

    /** Stworzyć nową grę z botem*/
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

    /** Dołączyć się do gry */
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

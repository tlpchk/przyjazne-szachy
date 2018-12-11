import {Component, OnInit} from '@angular/core';
import {UserService} from "../../_services/user.service";
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
<<<<<<< .merge_file_Zmten8
    createNewGame() {
        this.userService.createNewPlayer(firstPlayerColor).subscribe(playerId => {
            this.userService.createNewGame(playerId).subscribe(gameId => {
=======
    createNewCompetitionGame() {
        this.startService.createNewHumanPlayer(firstPlayerColor).subscribe(playerId => {
            this.startService.createNewGame(playerId,null).subscribe(gameId => {
>>>>>>> .merge_file_gNGCd8
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
<<<<<<< .merge_file_Zmten8
        this.userService.createNewPlayer(secondPlayerColor).subscribe(playerId => {
            this.userService.joinGame(gameId, playerId).subscribe(wasSuccessful => {
=======
        this.startService.createNewHumanPlayer(secondPlayerColor).subscribe(playerId => {
            this.startService.joinGame(gameId, playerId).subscribe(wasSuccessful => {
>>>>>>> .merge_file_gNGCd8
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

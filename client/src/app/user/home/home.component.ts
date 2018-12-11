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
    createNewGame() {
        this.userService.createNewPlayer(firstPlayerColor).subscribe(playerId => {
            this.userService.createNewGame(playerId).subscribe(gameId => {
                this.boardService.playerId = playerId;
                this.boardService.gameId.next(gameId);
            });
        });
    }

    //TODO RS: creating player creation should be another option
    joinGame(gameId: number) {
        this.userService.createNewPlayer(secondPlayerColor).subscribe(playerId => {
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

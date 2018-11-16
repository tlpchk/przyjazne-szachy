import {Component, OnInit} from '@angular/core';
import {StartService} from "../services/start.service";
import {GameDTO} from "../gameDTO";
import {GameService} from "../services/game.service";

@Component({
    selector: 'app-start',
    templateUrl: './start.component.html',
    styleUrls: ['./start.component.scss']
})
export class StartComponent implements OnInit {

    gameList: GameDTO[];
    game: GameDTO;


    constructor(private startService: StartService,
                private gameService: GameService) {
    }

    ngOnInit() {
        this.getGameList()
    }

    getGameList() {
        this.startService.getGameList().subscribe(list => this.gameList = list);
    }

    createNewGame() {
        this.startService.createNewGame().subscribe(newGame => {
            this.gameService.setGame(newGame);
        });
    }

    joinGame(gameId: number) {
        this.startService.joinGame(gameId).subscribe(newGame => {
            this.gameService.setGame(newGame);
        });
    }
}

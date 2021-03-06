import {Component, OnInit, ViewChild} from '@angular/core';
import {Cell} from '../../_models/cell';
import {BoardService} from '../../_services/board.service';
import {ChangeDTO} from '../../DTO/changeDTO';
import {CoordinatesAdapterService} from '../../_services/coordinates-adapter.service';
import {Piece, PieceType} from '../../_models/piece';
import {PopupComponent} from '../popup/popup.component';
import {Result} from '../../DTO/gameInfoDTO';
import {Move} from '../../_models/move';
import {TimerService} from "../../_services/timer.service";
import {Color} from "../../_models/color";


/** Komponent służący do wyświetlania planszy gry*/
@Component({
    selector: 'app-board',
    templateUrl: './board.component.html',
    styleUrls: ['./board.component.scss']
})
export class BoardComponent implements OnInit {
    /** Цyskakujące okienko*/
    @ViewChild(PopupComponent)
    private popup: PopupComponent;

    /** Aktualnie wybrana komórka*/
    selectedCell: Cell;
    /** Aktulny ruch*/
    move: Move;
    /** Tablica z komórkami planszy*/
    board: Cell[];
    /** Identyfikator gry*/
    gameId: number;
    /** Identyfikator ostatniej aktualizacji planszy*/
    lastUpdateId: number;
    /** Wynik gry*/
    result: Result;
    /** Flaga promocji*/
    isPromotion: boolean;
    /** Nazwa użytkownika-przeciwnika*/
    opponent : string;
    /** Flaga tury*/
    isMyTurn: boolean;
    /** Odświeżacz planszy*/
    updator;

    /** @ignore*/
    constructor(private boardService: BoardService,
                private coordinateService: CoordinatesAdapterService,
                private timerService: TimerService) {
    }

    /** Pobieranie planszy oraz czasu*/
    ngOnInit() {
        this.board = [];
        this.getGameId();
        this.move = new Move();
        this.selectedCell = null;
        this.timerService.startTimer();
    }

    /** Zaznaczenie aktualnej komórki oraz robienie ruch*/
    onSelect(cell: Cell) {
        this.selectedCell = cell;
        this.makeMove();
    }

    /** Robienie ruchu*/
    makeMove(): void {
        if (this.move.srcCell == null) {
            this.move.srcCell = this.selectedCell;
            this.boardService.getPossibleMove(this.selectedCell, this.gameId).subscribe(possibleMoves => {
                this.selectedCell.possibleMoves = this.boardService.getPossibleMoveArray(possibleMoves);
            });
        } else {
            this.move.destCell = this.selectedCell;
            if (this.move.srcCell !== this.move.destCell) {
                this.boardService.makeMove(this.move, this.gameId).subscribe(moveResponse => {
                    if (moveResponse.wasMoveValid) {
                        console.log('This move was valid');
                        this.updateBoard(moveResponse.listOfChanges);

                    } else {
                        console.log('Invalid move');
                    }
                });
            }
            // Reset move
            this.move = new Move();
            this.selectedCell = null;
        }
    }

    /** Pobieranie identyfikatora gry*/
    getGameId(): void {
        this.boardService.gameId$.subscribe(gameId => {
            this.gameId = gameId;
            this.timerService.gameId = this.gameId;
            this.timerService.playerId = this.boardService.playerId;
            this.getBoard();
            this.getGameInfo();
            const boardComponent = this;
            this.updator = setInterval(function () {
                if (boardComponent.result == null) {
                    boardComponent.getGameInfo();
                } else {
                    boardComponent.showEndGameMessage();
                    boardComponent.resetUpdator();
                }
            }, 500);
        });
    }

    /** Pokazywanie komunikatu końcowego*/
    private showEndGameMessage() {
        let resultText;
        switch (this.result) {
            case Result.first_player_won:
                resultText = "Wygrały białe";
                break;
            case Result.second_player_won:
                resultText = "Wygrały czarne";
                break;
            case Result.draw:
                resultText = "Remis";
                break;
        }
        this.popup.showMessage(resultText);
    }

    /** Pobieranie planszy*/
    getBoard(): void {
        this.boardService.getPieces(this.gameId)
            .subscribe(pieces => {
                this.board = this.boardService.getBoard(pieces);
                if (this.boardService.playerColor == Color.black) {
                    this.board.reverse();
                }
                this.lastUpdateId = 0;
                this.isMyTurn = false;
                this.result = null;
                this.isPromotion = false;
                this.opponent = '';
            });
    }

    /** Obsługa promocji*/
    promotion(pieceType: PieceType): void {
        this.boardService.promote(this.gameId, pieceType).subscribe(moveResponse => {
            const changes: ChangeDTO[] = moveResponse.listOfChanges;
            this.updateBoard(changes);
            this.popup.hide();
        });
    }

    /** Aktualizacja stanu gry*/
    getGameInfo(): void {
        console.log('GET GAME INFO');
        this.boardService.getGameInfo(this.gameId).subscribe(gameInfo => {
            this.result = gameInfo.gameResult;
            this.isMyTurn = gameInfo.myTurn;
            this.isPromotion = gameInfo.promotion;
            this.opponent = gameInfo.opponent;

            if (gameInfo.lastUpdateId > this.lastUpdateId) {
                while (gameInfo.lastUpdateId > this.lastUpdateId) {
                    this.lastUpdateId = this.lastUpdateId + 1;
                    this.boardService.getUpdate(this.gameId, this.lastUpdateId).subscribe(moveUpdate => {
                        this.updateBoard(moveUpdate.moveDTO.listOfChanges);
                    });
                }
            }
            if (this.isPromotion && this.isMyTurn) {
                this.popup.showPromotion();
            }
        }, error => {
            this.popup.showMessage('Stwórz grę');
            this.resetUpdator();
        });
    }

    /** Aktualizacja planszy*/
    private updateBoard(changes: ChangeDTO[]) {
        for (const c in changes) {
            let location = changes[c].location;
            const pieceColor = changes[c].color;
            const type = changes[c].type;
            const cellId = this.coordinateService.backendToFrontend(location.row, location.col, this.boardService.playerColor);
            const cellIndex = this.board.map(function (item) {
                return item.id;
            })
                .indexOf(cellId);
            let piece: Piece = new Piece(type, pieceColor);
            const possibleMoves: number[] = [];
            if (piece.type === null || piece.type === undefined) {
                piece = null;
            }
            this.board[cellIndex].piece = piece;
            this.board[cellIndex].possibleMoves = possibleMoves;
        }
    }

    /** Usuwanie aktualizatoras*/
    resetUpdator() {
        clearInterval(this.updator);
    }
}

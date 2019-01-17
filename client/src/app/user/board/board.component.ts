import {Component, OnInit, ViewChild} from '@angular/core';
import {Cell} from '../../_models/cell';
import {BoardService} from '../../_services/board.service';
import {ChangeDTO} from '../../_models/changeDTO';
import {CoordinatesAdapterService} from '../../_services/coordinates-adapter.service';
import {Piece, PieceType} from '../../_models/piece';
import {MoveUpdateDTO} from '../../_models/moveUpdateDTO';
import {PopupComponent} from '../popup/popup.component';
import {Router} from '@angular/router';
import {GameState, Result} from '../../_models/gameInfoDTO';

@Component({
    selector: 'app-board',
    templateUrl: './board.component.html',
    styleUrls: ['./board.component.scss']
})
export class BoardComponent implements OnInit {
    @ViewChild(PopupComponent)
    private popup: PopupComponent;

    selectedCell: Cell;
    move: Cell[];
    board: Cell[];
    gameId: number;
    lastUpdateId: number;
    isMyTurn: boolean;
    isGameFinished: boolean = false;
    result: Result;
    isPromotion = false;

    constructor(private boardService: BoardService,
                private coordinateService: CoordinatesAdapterService,) {
    }

    ngOnInit() {
        this.getGameId();
        this.move = [];
        this.board = [];
        this.selectedCell = null;
        /*if (this.board.length === 0) {
            this.popup.routerLink = '/user/home';
            this.popup.show('Stwórz nową grę');
        }*/
    }

    onSelect(cell: Cell) {
        this.selectedCell = cell;
        this.makeMove();
    }

    makeMove(): void {
        if (this.move.length === 0) {
            if (this.selectedCell.piece !== null) {
                this.move[0] = this.selectedCell;
                this.boardService.getPossibleMove(this.selectedCell, this.gameId).subscribe(possibleMoves => {
                    this.selectedCell.possibleMoves = this.boardService.getPossibleMoveArray(possibleMoves);
                });
            }
        } else {
            this.move[1] = this.selectedCell;
            if (this.move[0] !== this.move[1]) {
                this.boardService.makeMove(this.move, this.gameId).subscribe(moveResponse => {
                    if (moveResponse.wasMoveValid) {
                        console.log('This move was valid');
                        let changes: ChangeDTO[] = moveResponse.listOfChanges;
                        for (let c in changes) {
                            let location = changes[c].location;
                            let pieceColor = changes[c].color;
                            let type = changes[c].type;
                            let cellId = this.coordinateService.backendToFrontend(location.row, location.col);
                            let cellIndex = this.board.map(function (item) {
                                return item.id;
                            })
                                .indexOf(cellId);
                            let piece: Piece = new Piece(type, pieceColor);
                            let possibleMoves: number[] = [];
                            if (piece.type === null || piece.type === undefined) {
                                piece = null;
                            }
                            this.board[cellIndex].piece = piece;
                            this.board[cellIndex].possibleMoves = possibleMoves;
                        }
                        this.boardService.makeBotMove(this.gameId).subscribe(a => {
                        });
                    } else {
                        console.log("Invalid move");
                    }
                });

            }
            this.move = [];
            this.selectedCell = null;
        }
    }

    getGameId(): void {
        this.boardService.gameId$.subscribe(gameId => {
            this.gameId = gameId;
            this.getBoard();
        });
    }

    getBoard(): void {
        this.boardService.getPieces(this.gameId)
            .subscribe(pieces => {
                this.board = this.boardService.getBoard(pieces);
                this.lastUpdateId = 0;
                this.isMyTurn = false;
                this.isGameFinished = false;
                this.result = null;
                this.isPromotion = false;
                setTimeout(this.getGameInfo(), 1000);
            });
    }

    promotion(pieceType: PieceType): void {
        this.boardService.promote(this.gameId,pieceType).subscribe(moveResponse => {
            if (moveResponse.wasMoveValid) {
                console.log("This move was valid");
                let changes: ChangeDTO[] = moveResponse.listOfChanges;
                for (let c in changes) {
                    let location = changes[c].location;
                    let pieceColor = changes[c].color;
                    let type = changes[c].type;
                    let cellId = this.coordinateService.backendToFrontend(location.row, location.col);
                    let cellIndex = this.board.map(function (item) {
                        return item.id;
                    })
                        .indexOf(cellId);
                    let piece: Piece = new Piece(type, pieceColor);
                    let possibleMoves: number[] = [];
                    if (piece.type === null || piece.type === undefined) {
                        piece = null;
                    }
                    this.board[cellIndex].piece = piece;
                    this.board[cellIndex].possibleMoves = possibleMoves;
                }
                this.boardService.makeBotMove(this.gameId).subscribe(a => {
                    console.log("BOT MOVES: " + a);
                });
            } else {
                console.log("Invalid move");
            }
        });

    }

    getGameInfo(): void {
        this.boardService.getGameInfo(this.gameId).subscribe(gameInfo => {
            this.result = gameInfo.gameResult;
            this.isMyTurn = gameInfo.myTurn;
            this.isPromotion = gameInfo.promotion;
            this.isGameFinished = (gameInfo.gameState != GameState.game_running);
            if (gameInfo.lastUpdateId > this.lastUpdateId) {
                while (gameInfo.lastUpdateId > this.lastUpdateId) {
                    this.lastUpdateId = this.lastUpdateId + 1;
                    this.boardService.getUpdate(this.gameId, this.lastUpdateId).subscribe(moveUpdate => {
                        this.updateBoard(moveUpdate);
                    });
                }
                setTimeout(this.getGameInfo(), 1000);
            }
            else {
                setTimeout(this.getGameInfo(), 1000);
            }
        });
    }


    private updateBoard(moveUpdate: MoveUpdateDTO) {
        let changes: ChangeDTO[] = moveUpdate.moveDTO.listOfChanges;
        for (let c in changes) {
            let location = changes[c].location;
            let pieceColor = changes[c].color;
            let type = changes[c].type;
            let cellId = this.coordinateService.backendToFrontend(location.row, location.col);
            let cellIndex = this.board.map(function (item) {
                return item.id;
            })
                .indexOf(cellId);
            let piece: Piece = new Piece(type, pieceColor);
            let possibleMoves: number[] = [];
            if (piece.type === null || piece.type === undefined) {
                piece = null;
            }
            this.board[cellIndex].piece = piece;
            this.board[cellIndex].possibleMoves = possibleMoves;
        }
    }
}

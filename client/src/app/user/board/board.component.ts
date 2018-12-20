import {Component, OnInit, ViewChild} from '@angular/core';
import {Cell} from '../../_models/cell';
import {BoardService} from '../../_services/board.service';
import {ChangeDTO} from '../../_models/changeDTO';
import {CoordinatesAdapterService} from '../../_services/coordinates-adapter.service';
import {Piece} from '../../_models/piece';
import {MoveUpdateDTO} from '../../_models/moveUpdateDTO';
import {PopupComponent} from '../popup/popup.component';
import {Router} from '@angular/router';

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
    board: Cell[] = [];
    gameId: number;
    lastUpdateId = 0;

    constructor(private boardService: BoardService,
                private coordinateService: CoordinatesAdapterService,) {
    }

    ngOnInit() {
        this.getGameId();
        this.move = [];
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
                        const changes: ChangeDTO[] = moveResponse.listOfChanges;
                        console.log(moveResponse);
                        for (const c in changes) {
                            const location = changes[c].location;
                            const pieceColor = changes[c].color;
                            const type = changes[c].type;
                            console.log('row: ' + location.row + ' col: ' + location.col);
                            const cellId = this.coordinateService.backendToFrontend(location.row, location.col);
                            console.log('id: ' + cellId);
                            const cellIndex = this.board.map(function (item) {
                                return item.id;
                            })
                                .indexOf(cellId);
                            let piece: Piece = new Piece(type, pieceColor);
                            const possibleMoves: number[] = [];
                            if (piece.type === null || piece.type === undefined) {
                                piece = null;
                            }
                            console.log('piece: ' + piece);
                            console.log('board: ' + this.board);
                            console.log('index: ' + cellIndex);
                            this.board[cellIndex].piece = piece;
                            this.board[cellIndex].possibleMoves = possibleMoves;
                        }
                        this.boardService.makeBotMove(this.gameId).subscribe(a => {
                            console.log('BOT MOVES: ' + a);
                        });
                    } else {
                        console.log('Invalid move');
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
            console.log(gameId);
            this.getBoard();
        });
    }

    getBoard(): void {
        this.boardService.getPieces(this.gameId)
            .subscribe(pieces => {
                this.board = this.boardService.getBoard(pieces);
                // This probably shouldn't be done by setTimeout
                setTimeout(this.update(), 1000);
            });
    }

    update(): void {
        this.boardService.getLastUpdate(this.gameId).subscribe(lastMoveUpdate => {
            if (lastMoveUpdate.updateId > this.lastUpdateId) {
                while (lastMoveUpdate.updateId > this.lastUpdateId) {
                    this.lastUpdateId = this.lastUpdateId + 1;
                    this.boardService.getUpdate(this.gameId, this.lastUpdateId).subscribe(moveUpdate => {
                        this.updateBoard(moveUpdate);
                        setTimeout(this.update(), 1000);
                    });
                }
            } else {
                setTimeout(this.update(), 1000);
            }

        });
    }

    private updateBoard(moveUpdate: MoveUpdateDTO) {
        const changes: ChangeDTO[] = moveUpdate.moveDTO.listOfChanges;
        for (const c in changes) {
            const location = changes[c].location;
            const pieceColor = changes[c].color;
            const type = changes[c].type;
            const cellId = this.coordinateService.backendToFrontend(location.row, location.col);
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
}

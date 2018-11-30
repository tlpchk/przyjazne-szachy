import {Component, OnInit} from '@angular/core';
import {Cell} from '../../_models/cell';
import {BoardService} from '../../_services/board.service';
import {ChangeDTO} from "../../_models/changeDTO";
import {CoordinatesAdapterService} from "../../_services/coordinates-adapter.service";
import {Piece} from "../../_models/piece";

@Component({
    selector: 'app-board',
    templateUrl: './board.component.html',
    styleUrls: ['./board.component.scss']
})
export class BoardComponent implements OnInit {
    selectedCell: Cell;
    move: Cell[];
    board: Cell[] = [];
    gameId: number;
    lastUpdateId: number = 0;

    constructor(private boardService: BoardService,
                private coordinateService: CoordinatesAdapterService) {
    }

    ngOnInit() {
        this.getGameId();
        this.move = [];
        this.selectedCell = null;
    }

    onSelect(cell: Cell) {
        this.selectedCell = cell;
        this.makeMove();
    }

    makeMove(): void {
        if (this.move.length === 0) {
            if (this.selectedCell.piece !== null) {
                this.move[0] = this.selectedCell;
                //TODO RS: tu powinien być post żeby dostać possible moves
            }
        } else {
            this.move[1] = this.selectedCell;
            if (this.move[0] !== this.move[1]) {
                this.boardService.makeMove(this.move, this.gameId).subscribe(moveResponse => {
                    if (moveResponse.wasMoveValid) {
                        console.log("This move was valid");
                        let changes: ChangeDTO[] = moveResponse.listOfChanges;
                        for (let c in changes) {
                            let location = changes[c].location;
                            let pieceColor = changes[c].color;
                            let type = changes[c].type;
                            let cellId = this.coordinateService.backendToFrontend(location.row, location.column);
                            let cellIndex = this.board.map(function (item) {
                                return item.id;
                            })
                                .indexOf(cellId);
                            let piece: Piece = new Piece(type, pieceColor);
                            let possibleMoves: number[] = [];
                            if (piece.type === null || piece.type === undefined) {
                                piece = null;
                            }
                            // console.log(piece);
                            this.board[cellIndex].piece = piece;
                            this.board[cellIndex].possibleMoves = possibleMoves;
                        }

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
                //This probably shouldn't be done by setTimeout
                setTimeout(this.getLastUpdate(), 1000);
            });
    }

    getLastUpdate(): void {
        this.boardService.getLastUpdate(this.gameId).subscribe(moveUpdate => {
            if (moveUpdate.updateId !== this.lastUpdateId) {
                let changes: ChangeDTO[] = moveUpdate.moveDTO.listOfChanges;
                for (let c in changes) {
                    let location = changes[c].location;
                    let pieceColor = changes[c].color;
                    let type = changes[c].type;
                    let cellId = this.coordinateService.backendToFrontend(location.row, location.column);
                    let cellIndex = this.board.map(function (item) {
                        return item.id;
                    })
                        .indexOf(cellId);
                    let piece: Piece = new Piece(type, pieceColor);
                    let possibleMoves: number[] = [];
                    if (piece.type === null || piece.type === undefined) {
                        piece = null;
                    }
                    // console.log(piece);
                    this.board[cellIndex].piece = piece;
                    this.board[cellIndex].possibleMoves = possibleMoves;
                }
            }
            setTimeout(this.getLastUpdate(), 1000);
        });

    }
}

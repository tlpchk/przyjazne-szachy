import {Component, OnInit, ViewChild} from '@angular/core';
import {Cell} from '../../_models/cell';
import {BoardService} from '../../_services/board.service';
import {ChangeDTO} from '../../_models/changeDTO';
import {CoordinatesAdapterService} from '../../_services/coordinates-adapter.service';
import {Piece, PieceType} from '../../_models/piece';
import {MoveUpdateDTO} from '../../_models/moveUpdateDTO';
import {PopupComponent} from '../popup/popup.component';
import {GameState, Result} from '../../_models/gameInfoDTO';
import {Move} from '../../_models/move';
import {combineAll} from 'rxjs/operators';
import {error} from '@angular/compiler/src/util';
import {TimeInterval} from 'rxjs';

@Component({
    selector: 'app-board',
    templateUrl: './board.component.html',
    styleUrls: ['./board.component.scss']
})
export class BoardComponent implements OnInit {
    @ViewChild(PopupComponent)
    private popup: PopupComponent;

    selectedCell: Cell;
    move: Move;
    board: Cell[];
    gameId: number;
    lastUpdateId: number;
    isGameFinished: boolean;
    result: Result;
    isPromotion: boolean;
    isMyTurn: boolean;
    updator: number;

    constructor(private boardService: BoardService,
                private coordinateService: CoordinatesAdapterService) {
    }

    ngOnInit() {
        this.board = [];
        this.getGameId();
        this.move = new Move();
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
                        // TODO: Why bot's move is made here?
                        this.boardService.makeBotMove(this.gameId).subscribe(a => {
                        });
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

    getGameId(): void {
        this.boardService.gameId$.subscribe(gameId => {
            this.gameId = gameId;
            this.getBoard();
        },error => {console.log('ffuuuuuuuuuuuuucK');});
    }

    getBoard(): void {
        const boardComponent = this;
        this.boardService.getPieces(this.gameId)
            .subscribe(pieces => {
                this.board = this.boardService.getBoard(pieces);
                this.lastUpdateId = 0;
                this.isMyTurn = false;
                this.isGameFinished = false;
                this.result = null;
                this.isPromotion = false;
                this.updator = setInterval(function() {boardComponent.getGameInfo(); }, 500);
            });
    }

    promotion(pieceType: PieceType): void {
        this.boardService.promote(this.gameId, pieceType).subscribe(moveResponse => {
            const changes: ChangeDTO[] = moveResponse.listOfChanges;
            this.updateBoard(changes);
            this.boardService.makeBotMove(this.gameId).subscribe(a => {
                console.log('BOT MOVES: ' + a);
            });
            this.popup.hide();
         });
    }

    getGameInfo(): void {
        console.log('GET GAME INFO');
        this.boardService.getGameInfo(this.gameId).subscribe(gameInfo => {
            this.result = gameInfo.gameResult;
            this.isMyTurn = gameInfo.myTurn;
            this.isPromotion = gameInfo.promotion;
            this.isGameFinished = (gameInfo.gameState !== GameState.game_running);
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
        }, error => {this.popup.routerLink = 'user/home'; this.popup.showMessage('Stwórz grę'); this.resetUpdator(); } );
    }


    private updateBoard(changes: ChangeDTO[] ) {
        for (const c in changes) {
            let location = changes[c].location;
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

    resetUpdator(){
        clearInterval(this.updator);
    }
}

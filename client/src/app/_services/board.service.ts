import {Injectable} from '@angular/core';
import {Observable, of, ReplaySubject} from 'rxjs';
import {Cell} from '../_models/cell';
import {MessageService} from './message.service';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {CoordinatesAdapterService} from "./coordinates-adapter.service";
import {MoveResponseDTO} from "../_models/moveResponseDTO";
import {MoveUpdateDTO} from "../_models/moveUpdateDTO";
import {CreateMoveDTO} from "../_models/createMoveDTO";
import {PieceDTO} from "../_models/pieceDTO";
import {Color} from "../_models/color";
import {Piece, PieceType} from "../_models/piece";
import {PositionDTO} from "../_models/positionDTO";
import {GameInfoDTO} from "../_models/gameInfoDTO";
import {PromotionDTO} from "../_models/promotionDTO";
import {Move} from '../_models/move';

const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
    providedIn: 'root'
})

export class BoardService {
    playerId: number;
    playerColor: Color;
    gameId = new ReplaySubject<number>();
    gameId$ = this.gameId.asObservable();

    private gamesUrl = 'api/games';
    private moveSubUrl = '/move';
    private boardSubUrl = '/board';
    private updateSubUrl = '/update';
    private possibleMovesSubUrl = '/possibleMoves';
    private promotionSubUrl = '/promote';

    constructor(private http: HttpClient,
                private messageService: MessageService,
                private coordinatesService: CoordinatesAdapterService) {
    }

    getPieces(gameId: number): Observable<PieceDTO[]> {
        let url = this.gamesUrl + "/" + gameId + this.boardSubUrl;
        return this.http.get<PieceDTO[]>(url);
    }

    getBoard(pieces: PieceDTO[]): Cell[] {
        let board: Cell[] = [];
        for (let i in pieces) {
            let id = this.coordinatesService.backendToFrontend(pieces[i].row, pieces[i].column,this.playerColor);
            let sum = pieces[i].row + pieces[i].column;
            let color: Color = (sum % 2 === 0) ? Color.white : Color.black;
            let piece: Piece = new Piece(pieces[i].type, pieces[i].color);
            let possibleMoves: number[] = [];
            for (let j in pieces[i].possibleMoves) {
                let row = pieces[i].possibleMoves[j].row;
                let column = pieces[i].possibleMoves[j].col;
                let possibleMoveId = this.coordinatesService.backendToFrontend(row, column,this.playerColor);
                possibleMoves.push(possibleMoveId);
            }
            if (piece.type === null || piece.type === undefined) {
                piece = null;
            }
            let cell = new Cell(id, color, piece, possibleMoves);
            console.log(cell);
            board.push(cell);
        }
        return board;
    }

    private log(message: string) {
        this.messageService.add(`BoardService: ${message}`);
    }


    makeMove(move : Move , gameId: number): Observable<MoveResponseDTO> {
        let url = this.gamesUrl + "/" + gameId + this.moveSubUrl;
        let origin = this.coordinatesService.frontendToBackend(move.srcCell.id,this.playerColor);
        let destination = this.coordinatesService.frontendToBackend(move.destCell.id,this.playerColor);
        let createMoveDTO = new CreateMoveDTO(this.playerId,
            origin, destination);
        return this.http.post<MoveResponseDTO>(url, createMoveDTO, httpOptions);
    }

    /**
     * Handle Http operation that failed.
     * Let the app continue.
     * @param operation - name of the operation that failed
     * @param result - optional value to return as the observable playerPoints
     */
    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {

            // TODO: send the error to remote logging infrastructure
            console.error(error); // log to console instead

            // TODO: better job of transforming error for user consumption
            this.log(`${operation} failed: ${error.message}`);

            // Let the app keep running by returning an empty playerPoints.
            return of(result as T);
        };
    }

    /*TODO: Don't pipe logs*/

    /*makeMove(moveElement: Cell, moveElement2: Cell) {
        forkJoin(
            this.updateCell(moveElement),
            this.updateCell(moveElement2)
        ).pipe(
            map(([first, second]) => {
                // combineLatest returns an array of values, here we map those values to an object
                return { first, second };
            })
        );
    }*/

    getUpdate(gameId: number, updateId: number): Observable<MoveUpdateDTO> {
        let url = this.gamesUrl + "/" + gameId + this.updateSubUrl + "/" + updateId;
        return this.http.get<MoveUpdateDTO>(url);
    }

    getPossibleMove(selectedCell: Cell, gameId: number) {
        let url = this.gamesUrl + "/" + gameId + this.possibleMovesSubUrl;
        let position = this.coordinatesService.frontendToBackend(selectedCell.id,this.playerColor);
        return this.http.post<PositionDTO[]>(url, position, httpOptions);
    }

    getPossibleMoveArray(possibleMoves: PositionDTO[]) {
        let possibleMovesToReturn: number[] = [];
        for (let i in possibleMoves) {
            let possibleMove = possibleMoves[i];
            let possibleMoveId = this.coordinatesService.backendToFrontend(possibleMove.row, possibleMove.col,this.playerColor);
            possibleMovesToReturn.push(possibleMoveId);
        }
        return possibleMovesToReturn;
    }

    getGameInfo(gameId: number): Observable<GameInfoDTO> {
        let url = this.gamesUrl + "/" + gameId;
        return this.http.post<GameInfoDTO>(url, this.playerId, httpOptions);
    }

    promote(gameId: number, pieceType: PieceType): Observable<MoveResponseDTO> {
        let url = this.gamesUrl + "/" + gameId + this.promotionSubUrl;
        let promotionDTO = new PromotionDTO(this.playerId, pieceType);
        return this.http.post<MoveResponseDTO>(url, promotionDTO, httpOptions);

    }
}

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
import {Piece} from "../_models/piece";
import {PositionDTO} from "../_models/positionDTO";

const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
    providedIn: 'root'
})

export class BoardService {
    playerId: number;
    gameId = new ReplaySubject<number>();
    gameId$ = this.gameId.asObservable();

    private gamesUrl = 'http://localhost:8080/games';
    private moveSubUrl = '/move';
    private boardSubUrl = '/board';
    private updateSubUrl = '/update';
    private possibleMovesSubUrl = '/possibleMoves';
    private botSubUrl = '/bot';

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
            let id = this.coordinatesService.backendToFrontend(pieces[i].row, pieces[i].column);
            let sum = pieces[i].row + pieces[i].column;
            let color: Color = (sum % 2 === 0) ? Color.white : Color.black;
            let piece: Piece = new Piece(pieces[i].type, pieces[i].color);
            let possibleMoves: number[] = [];
            for (let j in pieces[i].possibleMoves) {
                let row = pieces[i].possibleMoves[j].row;
                let column = pieces[i].possibleMoves[j].col;
                let possibleMoveId = this.coordinatesService.backendToFrontend(row, column);
                possibleMoves.push(possibleMoveId);
            }
            if (piece.type === null || piece.type === undefined) {
                piece = null;
            }
            let cell = new Cell(id, color, piece, possibleMoves);
            board.push(cell);
        }
        return board;
    }

    private log(message: string) {
        this.messageService.add(`BoardService: ${message}`);
    }


    makeMove(cell: Cell[], gameId: number): Observable<MoveResponseDTO> {
        let url = this.gamesUrl + "/" + gameId + this.moveSubUrl;
        let origin = this.coordinatesService.frontendToBackend(cell[0].id);
        let destination = this.coordinatesService.frontendToBackend(cell[1].id);
        let createMoveDTO = new CreateMoveDTO(this.playerId,
            origin, destination);
        return this.http.post<MoveResponseDTO>(url, createMoveDTO, httpOptions);
    }

    makeBotMove(gameId: number): Observable<MoveResponseDTO> {
        let url = this.gamesUrl + "/" + gameId + this.botSubUrl;
        return this.http.get<MoveResponseDTO>(url);
    }

    /**
     * Handle Http operation that failed.
     * Let the app continue.
     * @param operation - name of the operation that failed
     * @param result - optional value to return as the observable result
     */
    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {

            // TODO: send the error to remote logging infrastructure
            console.error(error); // log to console instead

            // TODO: better job of transforming error for user consumption
            this.log(`${operation} failed: ${error.message}`);

            // Let the app keep running by returning an empty result.
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
    getLastUpdate(gameId: number): Observable<MoveUpdateDTO> {
        let url = this.gamesUrl + "/" + gameId + this.updateSubUrl;
        return this.http.get<MoveUpdateDTO>(url);
    }

    getUpdate(gameId: number, updateId: number): Observable<MoveUpdateDTO> {
        let url = this.gamesUrl + "/" + gameId + this.updateSubUrl + "/" + updateId;
        return this.http.get<MoveUpdateDTO>(url);
    }

    getPossibleMove(selectedCell: Cell, gameId: number) {
        let url = this.gamesUrl + "/" + gameId + this.possibleMovesSubUrl;
        let position = this.coordinatesService.frontendToBackend(selectedCell.id);
        return this.http.post<PositionDTO[]>(url, position, httpOptions);
    }

    getPossibleMoveArray(possibleMoves: PositionDTO[]) {
        let possibleMovesToReturn: number[] = [];
        for (let i in possibleMoves) {
            let possibleMove = possibleMoves[i];
            let possibleMoveId = this.coordinatesService.backendToFrontend(possibleMove.row, possibleMove.col);
            possibleMovesToReturn.push(possibleMoveId);
        }
        return possibleMovesToReturn;
    }
}

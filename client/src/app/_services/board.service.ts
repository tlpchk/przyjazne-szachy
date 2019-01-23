import {Injectable} from '@angular/core';
import {Observable, ReplaySubject} from 'rxjs';
import {Cell} from '../_models/cell';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {CoordinatesAdapterService} from "./coordinates-adapter.service";
import {MoveResponseDTO} from "../DTO/moveResponseDTO";
import {MoveUpdateDTO} from "../DTO/moveUpdateDTO";
import {CreateMoveDTO} from "../DTO/createMoveDTO";
import {PieceDTO} from "../DTO/pieceDTO";
import {Color} from "../_models/color";
import {Piece, PieceType} from "../_models/piece";
import {PositionDTO} from "../DTO/positionDTO";
import {GameInfoDTO} from "../DTO/gameInfoDTO";
import {PromotionDTO} from "../DTO/promotionDTO";
import {Move} from '../_models/move';
import {
    boardSubUrl,
    gamesUrl,
    httpOptions,
    moveSubUrl,
    possibleMovesSubUrl,
    promotionSubUrl,
    updateSubUrl
} from "./httpConection";


/** Serwis, który służy do pobierania informacji o planszy*/
@Injectable({
    providedIn: 'root'
})
export class BoardService {
    /** Id gracza*/
    playerId: number;
    /** Kolor gracza*/
    playerColor: Color;
    /** Id gry*/
    gameId = new ReplaySubject<number>();
    /** Id gry (Observable)*/
    gameId$ = this.gameId.asObservable();

    /** @ignore*/
    constructor(private http: HttpClient,
                private coordinatesService: CoordinatesAdapterService) {
    }

    /**
     * Pobieranie pozycji figurek z serweru
     * @param gameId Id gry
     */
    getPieces(gameId: number): Observable<PieceDTO[]> {
        let url = gamesUrl + "/" + gameId + boardSubUrl;
        return this.http.get<PieceDTO[]>(url);
    }

    /**
     * Pobieranie planszy
     * @param pieces lista figurek
     */
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

    /**
     * Wysyłanie do serweru informacji o wykonanym ruchu
     */
    makeMove(move : Move , gameId: number): Observable<MoveResponseDTO> {
        let url = gamesUrl + "/" + gameId + moveSubUrl;
        let origin = this.coordinatesService.frontendToBackend(move.srcCell.id,this.playerColor);
        let destination = this.coordinatesService.frontendToBackend(move.destCell.id,this.playerColor);
        let createMoveDTO = new CreateMoveDTO(this.playerId,
            origin, destination);
        return this.http.post<MoveResponseDTO>(url, createMoveDTO, httpOptions);
    }

    /** Aktualizaja planszy*/
    getUpdate(gameId: number, updateId: number): Observable<MoveUpdateDTO> {
        let url = gamesUrl + "/" + gameId + updateSubUrl + "/" + updateId;
        return this.http.get<MoveUpdateDTO>(url);
    }

    /** Obliczanie możliwych ruchów dla wybranej komórki*/
    getPossibleMove(selectedCell: Cell, gameId: number) {
        let url = gamesUrl + "/" + gameId + possibleMovesSubUrl;
        let position = this.coordinatesService.frontendToBackend(selectedCell.id,this.playerColor);
        return this.http.post<PositionDTO[]>(url, position, httpOptions);
    }

    /** Konwertacja możliwych ruchów*/
    getPossibleMoveArray(possibleMoves: PositionDTO[]) {
        let possibleMovesToReturn: number[] = [];
        for (let i in possibleMoves) {
            let possibleMove = possibleMoves[i];
            let possibleMoveId = this.coordinatesService.backendToFrontend(possibleMove.row, possibleMove.col,this.playerColor);
            possibleMovesToReturn.push(possibleMoveId);
        }
        return possibleMovesToReturn;
    }

    /** Pobieranie informacji o grze*/
    getGameInfo(gameId: number): Observable<GameInfoDTO> {
        let url = gamesUrl + "/" + gameId;
        return this.http.post<GameInfoDTO>(url, this.playerId, httpOptions);
    }

    /** Obsługa promocji*/
    promote(gameId: number, pieceType: PieceType): Observable<MoveResponseDTO> {
        let url = gamesUrl + "/" + gameId + promotionSubUrl;
        let promotionDTO = new PromotionDTO(this.playerId, pieceType);
        return this.http.post<MoveResponseDTO>(url, promotionDTO, httpOptions);

    }
}

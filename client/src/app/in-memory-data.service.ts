import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Injectable } from '@angular/core';
import {PieceType} from "./piece";
import {Color} from "./color";
import {Cell} from "./cell";

@Injectable({
    providedIn: 'root',
})
export class InMemoryDataService implements InMemoryDbService {
    createDb() {
        const whiteKing =  {type: PieceType.KING, color: Color.white};
        const whiteQueen =  {type: PieceType.QUEEN, color: Color.white};
        const whitePawn =  {type: PieceType.PAWN, color: Color.white};
        const whiteRook =  {type: PieceType.ROOK, color: Color.white};
        const whiteKnight =  {type: PieceType.KNIGHT, color: Color.white};
        const whiteBishop =  {type: PieceType.BISHOP, color: Color.white};

        const blackKing =  {type: PieceType.KING, color: Color.black};
        const blackQueen =  {type: PieceType.QUEEN, color: Color.black};
        const blackPawn =  {type: PieceType.PAWN, color: Color.black};
        const blackRook =  {type: PieceType.ROOK, color: Color.black};
        const blackKnight =  {type: PieceType.KNIGHT, color: Color.black};
        const blackBishop =  {type: PieceType.BISHOP, color: Color.black};


        const board = [
            {id : 1 , cell_color : Color.white, piece: whiteRook},
            {id : 2 , cell_color : Color.black, piece: whiteKnight},
            {id : 3 , cell_color : Color.white, piece: whiteBishop},
            {id : 4 , cell_color : Color.black, piece: whiteKing},
            {id : 5 , cell_color : Color.white, piece: whiteQueen},
            {id : 6 , cell_color : Color.black, piece: whiteBishop},
            {id : 7 , cell_color : Color.white, piece: whiteKnight},
            {id : 8 , cell_color : Color.black, piece: whiteRook},
            {id : 9 , cell_color : Color.black, piece: whitePawn},
            {id : 10 , cell_color : Color.white, piece: whitePawn},
            {id : 11 , cell_color : Color.black, piece: whitePawn},
            {id : 12 , cell_color : Color.white, piece: whitePawn},
            {id : 13 , cell_color : Color.black, piece: whitePawn},
            {id : 14 , cell_color : Color.white, piece: whitePawn},
            {id : 15 , cell_color : Color.black, piece: whitePawn},
            {id : 16 , cell_color : Color.white, piece: whitePawn},
            {id : 17 , cell_color : Color.white, piece: null},
            {id : 18 , cell_color : Color.black, piece: null},
            {id : 19 , cell_color : Color.white, piece: null},
            {id : 20 , cell_color : Color.black, piece: null},
            {id : 21 , cell_color : Color.white, piece: null},
            {id : 22 , cell_color : Color.black, piece: null},
            {id : 23 , cell_color : Color.white, piece: null},
            {id : 24 , cell_color : Color.black, piece: null},
            {id : 25 , cell_color : Color.black, piece: null},
            {id : 26 , cell_color : Color.white, piece: null},
            {id : 27 , cell_color : Color.black, piece: null},
            {id : 28 , cell_color : Color.white, piece: null},
            {id : 29 , cell_color : Color.black, piece: null},
            {id : 30 , cell_color : Color.white, piece: null},
            {id : 31 , cell_color : Color.black, piece: null},
            {id : 32 , cell_color : Color.white, piece: null},
            {id : 33 , cell_color : Color.white, piece: null},
            {id : 34 , cell_color : Color.black, piece: null},
            {id : 35 , cell_color : Color.white, piece: null},
            {id : 36 , cell_color : Color.black, piece: null},
            {id : 37 , cell_color : Color.white, piece: null},
            {id : 38 , cell_color : Color.black, piece: null},
            {id : 39 , cell_color : Color.white, piece: null},
            {id : 40 , cell_color : Color.black, piece: null},
            {id : 41 , cell_color : Color.black, piece: null},
            {id : 42 , cell_color : Color.white, piece: null},
            {id : 43 , cell_color : Color.black, piece: null},
            {id : 44 , cell_color : Color.white, piece: null},
            {id : 45 , cell_color : Color.black, piece: null},
            {id : 46 , cell_color : Color.white, piece: null},
            {id : 47 , cell_color : Color.black, piece: null},
            {id : 48 , cell_color : Color.white, piece: null},
            {id : 49 , cell_color : Color.white, piece: blackPawn},
            {id : 50 , cell_color : Color.black, piece: blackPawn},
            {id : 51 , cell_color : Color.white, piece: blackPawn},
            {id : 52 , cell_color : Color.black, piece: blackPawn},
            {id : 53 , cell_color : Color.white, piece: blackPawn},
            {id : 54 , cell_color : Color.black, piece: blackPawn},
            {id : 55 , cell_color : Color.white, piece: blackPawn},
            {id : 56 , cell_color : Color.black, piece: blackPawn},
            {id : 57 , cell_color : Color.black, piece: blackRook},
            {id : 58 , cell_color : Color.white, piece: blackKnight},
            {id : 59 , cell_color : Color.black, piece: blackBishop},
            {id : 60 , cell_color : Color.white, piece: blackQueen},
            {id : 61 , cell_color : Color.black, piece: blackKing},
            {id : 62 , cell_color : Color.white, piece: blackBishop},
            {id : 63 , cell_color : Color.black, piece: blackKnight},
            {id : 64 , cell_color : Color.white, piece: blackRook},
        ];
        return {board};
    }

    // Overrides the genId method to ensure that a hero always has an id.
    // If the heroes array is empty,
    // the method below returns the initial number (11).
    // if the heroes array is not empty, the method below returns the highest
    // hero id + 1.

    genId(board: Cell[]): number {
        return board.length > 0 ? Math.max(...board.map(cell => cell.id)) + 1 : 11;
    }
}

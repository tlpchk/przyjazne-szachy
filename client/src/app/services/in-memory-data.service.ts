import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Injectable } from '@angular/core';
import {PieceType} from '../piece';
import {Color} from '../color';
import {Cell} from '../cell';

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
            new Cell( 1 , Color.white, whiteRook, []),
            new Cell(2 , Color.black,  whiteKnight, []),
            new Cell(3 , Color.white,  whiteBishop, []),
            new Cell(4 , Color.black,  whiteKing, []),
            new Cell(5 , Color.white,  whiteQueen, []),
            new Cell(6 , Color.black,  whiteBishop, []),
            new Cell(7 , Color.white,  whiteKnight, []),
            new Cell(8 , Color.black,  whiteRook, []),
            new Cell(9 , Color.black,  whitePawn, []),
            new Cell(10 , Color.white,  whitePawn, []),
            new Cell(11 , Color.black,  whitePawn, []),
            new Cell(12 , Color.white,  whitePawn, []),
            new Cell(13 , Color.black,  whitePawn, []),
            new Cell(14 , Color.white,  whitePawn, []),
            new Cell(15 , Color.black,  whitePawn, []),
            new Cell(16 , Color.white,  whitePawn, []),
            new Cell(17 , Color.white,  null, []),
            new Cell(18 , Color.black,  null, []),
            new Cell(19 , Color.white,  null, []),
            new Cell(20 , Color.black,  null, []),
            new Cell(21 , Color.white,  null, []),
            new Cell(22 , Color.black,  null, []),
            new Cell(23 , Color.white,  null, []),
            new Cell(24 , Color.black,  null, []),
            new Cell(25 , Color.black,  null, []),
            new Cell(26 , Color.white,  null, []),
            new Cell(27 , Color.black,  null, []),
            new Cell(28 , Color.white,  null, []),
            new Cell(29 , Color.black,  null, []),
            new Cell(30 , Color.white,  null, []),
            new Cell(31 , Color.black,  null, []),
            new Cell(32 , Color.white,  null, []),
            new Cell(33 , Color.white,  null, []),
            new Cell(34 , Color.black,  null, []),
            new Cell(35 , Color.white,  null, []),
            new Cell(36 , Color.black,  null, []),
            new Cell(37 , Color.white,  null, []),
            new Cell(38 , Color.black,  null, []),
            new Cell(39 , Color.white,  null, []),
            new Cell(40 , Color.black,  null, []),
            new Cell(41 , Color.black,  null, []),
            new Cell(42 , Color.white,  null, []),
            new Cell(43 , Color.black,  null, []),
            new Cell(44 , Color.white,  null, []),
            new Cell(45 , Color.black,  null, []),
            new Cell(46 , Color.white,  null, []),
            new Cell(47 , Color.black,  null, []),
            new Cell(48 , Color.white,  null, []),
            new Cell(49 , Color.white,  blackPawn, []),
            new Cell(50 , Color.black,  blackPawn, []),
            new Cell(51 , Color.white,  blackPawn, []),
            new Cell(52 , Color.black,  blackPawn, []),
            new Cell(53 , Color.white,  blackPawn, []),
            new Cell(54 , Color.black,  blackPawn, []),
            new Cell(55 , Color.white,  blackPawn, []),
            new Cell(56 , Color.black,  blackPawn, []),
            new Cell(57 , Color.black,  blackRook, []),
            new Cell(58 , Color.white,  blackKnight, []),
            new Cell(59 , Color.black,  blackBishop, []),
            new Cell(60 , Color.white,  blackQueen, []),
            new Cell(61 , Color.black,  blackKing, []),
            new Cell(62 , Color.white,  blackBishop, []),
            new Cell(63 , Color.black,  blackKnight, []),
            new Cell(64 , Color.white,  blackRook, []),
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

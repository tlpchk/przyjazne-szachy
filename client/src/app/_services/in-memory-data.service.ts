import {Injectable} from '@angular/core';
import {PieceType} from '../_models/piece';
import {Color} from '../_models/color';
import {Cell} from '../_models/cell';

@Injectable({
    providedIn: 'root',
})
export class InMemoryDataService {
    // createDb() {
    //     const whiteKing =  {type: PieceType.KING, color: Color.white};
    //     const whiteQueen =  {type: PieceType.QUEEN, color: Color.white};
    //     const whitePawn =  {type: PieceType.PAWN, color: Color.white};
    //     const whiteRook =  {type: PieceType.ROOK, color: Color.white};
    //     const whiteKnight =  {type: PieceType.KNIGHT, color: Color.white};
    //     const whiteBishop =  {type: PieceType.BISHOP, color: Color.white};
    //
    //     const blackKing =  {type: PieceType.KING, color: Color.black};
    //     const blackQueen =  {type: PieceType.QUEEN, color: Color.black};
    //     const blackPawn =  {type: PieceType.PAWN, color: Color.black};
    //     const blackRook =  {type: PieceType.ROOK, color: Color.black};
    //     const blackKnight =  {type: PieceType.KNIGHT, color: Color.black};
    //     const blackBishop =  {type: PieceType.BISHOP, color: Color.black};
    //         const board = [
    //         new Cell( 1 , Color.black, whiteRook, []),
    //         new Cell(2 , Color.white,  whiteKnight, [17,19]),
    //         new Cell(3 , Color.black,  whiteBishop, []),
    //         new Cell(4 , Color.white,  whiteQueen, []),
    //         new Cell(5 , Color.black,  whiteKing, []),
    //         new Cell(6 , Color.white,  whiteBishop, []),
    //         new Cell(7 , Color.black,  whiteKnight, []),
    //         new Cell(8 , Color.white,  whiteRook, []),
    //         new Cell(9 , Color.white,  whitePawn, []),
    //         new Cell(10 , Color.black,  whitePawn, []),
    //         new Cell(11 , Color.white,  whitePawn, []),
    //         new Cell(12 , Color.black,  whitePawn, []),
    //         new Cell(13 , Color.white,  whitePawn, []),
    //         new Cell(14 , Color.black,  whitePawn, []),
    //         new Cell(15 , Color.white,  whitePawn, []),
    //         new Cell(16 , Color.black,  whitePawn, []),
    //         new Cell(17 , Color.black,  null, []),
    //         new Cell(18 , Color.white,  null, []),
    //         new Cell(19 , Color.black,  null, []),
    //         new Cell(20 , Color.white,  null, []),
    //         new Cell(21 , Color.black,  null, []),
    //         new Cell(22 , Color.white,  null, []),
    //         new Cell(23 , Color.black,  null, []),
    //         new Cell(24 , Color.white,  null, []),
    //         new Cell(25 , Color.white,  null, []),
    //         new Cell(26 , Color.black,  null, []),
    //         new Cell(27 , Color.white,  null, []),
    //         new Cell(28 , Color.black,  null, []),
    //         new Cell(29 , Color.white,  null, []),
    //         new Cell(30 , Color.black,  null, []),
    //         new Cell(31 , Color.white,  null, []),
    //         new Cell(32 , Color.black,  null, []),
    //         new Cell(33 , Color.black,  null, []),
    //         new Cell(34 , Color.white,  null, []),
    //         new Cell(35 , Color.black,  null, []),
    //         new Cell(36 , Color.white,  null, []),
    //         new Cell(37 , Color.black,  null, []),
    //         new Cell(38 , Color.white,  null, []),
    //         new Cell(39 , Color.black,  null, []),
    //         new Cell(40 , Color.white,  null, []),
    //         new Cell(41 , Color.white,  null, []),
    //         new Cell(42 , Color.black,  null, []),
    //         new Cell(43 , Color.white,  null, []),
    //         new Cell(44 , Color.black,  null, []),
    //         new Cell(45 , Color.white,  null, []),
    //         new Cell(46 , Color.black,  null, []),
    //         new Cell(47 , Color.white,  null, []),
    //         new Cell(48 , Color.black,  null, []),
    //         new Cell(49 , Color.black,  blackPawn, []),
    //         new Cell(50 , Color.white,  blackPawn, []),
    //         new Cell(51 , Color.black,  blackPawn, []),
    //         new Cell(52 , Color.white,  blackPawn, []),
    //         new Cell(53 , Color.black,  blackPawn, []),
    //         new Cell(54 , Color.white,  blackPawn, []),
    //         new Cell(55 , Color.black,  blackPawn, []),
    //         new Cell(56 , Color.white,  blackPawn, []),
    //         new Cell(57 , Color.white,  blackRook, []),
    //         new Cell(58 , Color.black,  blackKnight, []),
    //         new Cell(59 , Color.white,  blackBishop, []),
    //         new Cell(60 , Color.black,  blackQueen, []),
    //         new Cell(61 , Color.white,  blackKing, []),
    //         new Cell(62 , Color.black,  blackBishop, []),
    //         new Cell(63 , Color.white,  blackKnight, []),
    //         new Cell(64 , Color.black,  blackRook, []),
    //     ];
    //     return {board};
    // }

    createDb() {
        const board = [
            new Cell(1, Color.black, null, []),
            new Cell(2, Color.white, null, []),
            new Cell(3, Color.black, null, []),
            new Cell(4, Color.white, null, []),
            new Cell(5, Color.black, null, []),
            new Cell(6, Color.white, null, []),
            new Cell(7, Color.black, null, []),
            new Cell(8, Color.white, null, []),
            new Cell(9, Color.white, null, []),
            new Cell(10, Color.black, null, []),
            new Cell(11, Color.white, null, []),
            new Cell(12, Color.black, null, []),
            new Cell(13, Color.white, null, []),
            new Cell(14, Color.black, null, []),
            new Cell(15, Color.white, null, []),
            new Cell(16, Color.black, null, []),
            new Cell(17, Color.black, null, []),
            new Cell(18, Color.white, null, []),
            new Cell(19, Color.black, null, []),
            new Cell(20, Color.white, null, []),
            new Cell(21, Color.black, null, []),
            new Cell(22, Color.white, null, []),
            new Cell(23, Color.black, null, []),
            new Cell(24, Color.white, null, []),
            new Cell(25, Color.white, null, []),
            new Cell(26, Color.black, null, []),
            new Cell(27, Color.white, null, []),
            new Cell(28, Color.black, null, []),
            new Cell(29, Color.white, null, []),
            new Cell(30, Color.black, null, []),
            new Cell(31, Color.white, null, []),
            new Cell(32, Color.black, null, []),
            new Cell(33, Color.black, null, []),
            new Cell(34, Color.white, null, []),
            new Cell(35, Color.black, null, []),
            new Cell(36, Color.white, null, []),
            new Cell(37, Color.black, null, []),
            new Cell(38, Color.white, null, []),
            new Cell(39, Color.black, null, []),
            new Cell(40, Color.white, null, []),
            new Cell(41, Color.white, null, []),
            new Cell(42, Color.black, null, []),
            new Cell(43, Color.white, null, []),
            new Cell(44, Color.black, null, []),
            new Cell(45, Color.white, null, []),
            new Cell(46, Color.black, null, []),
            new Cell(47, Color.white, null, []),
            new Cell(48, Color.black, null, []),
            new Cell(49, Color.black, null, []),
            new Cell(50, Color.white, null, []),
            new Cell(51, Color.black, null, []),
            new Cell(52, Color.white, null, []),
            new Cell(53, Color.black, null, []),
            new Cell(54, Color.white, null, []),
            new Cell(55, Color.black, null, []),
            new Cell(56, Color.white, null, []),
            new Cell(57, Color.white, null, []),
            new Cell(58, Color.black, null, []),
            new Cell(59, Color.white, null, []),
            new Cell(60, Color.black, null, []),
            new Cell(61, Color.white, null, []),
            new Cell(62, Color.black, null, []),
            new Cell(63, Color.white, null, []),
            new Cell(64, Color.black, null, []),
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

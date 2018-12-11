import {Color} from './color';

export enum PieceType {
    PAWN = 'pawn',
    ROOK = 'rook',
    BISHOP = 'bishop',
    KNIGHT = 'knight',
    QUEEN = 'queen',
    KING = 'king'
}

export class Piece {
    constructor(public type: PieceType,
                public color: Color) {
    }
}

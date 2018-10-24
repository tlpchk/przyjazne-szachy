import {Color} from './cell';

export enum PieceType {
    PAWN = 'pawn',
    ROOK = 'rook',
    BISHOP = 'bishop',
    KNIGHT = 'knight',
    QUEEN = 'queen',
    KING = 'king'
}

export class Piece {
    type: PieceType;
    color: Color;
}

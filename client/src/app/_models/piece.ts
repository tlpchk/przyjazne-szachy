import {Color} from './color';

/** Dostępne typy figurek */
export enum PieceType {
    PAWN = 'pawn',
    ROOK = 'rook',
    BISHOP = 'bishop',
    KNIGHT = 'knight',
    QUEEN = 'queen',
    KING = 'king'
}

/** Klasa do wyświetlenia figurki*/
export class Piece {
    /** Typ figurki*/
    public type: PieceType;
    /** Kolor figurki*/
    public color: Color;

    /** @ignore*/
    constructor(type: PieceType, color: Color) {
        this.type = type;
        this.color = color;
    }
}

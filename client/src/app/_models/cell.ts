import {Color} from './color';
import {Piece} from './piece';

/* Kl*/
export class Cell {
    private _id: number;
    private _cell_color: Color;
    private _piece: Piece;
    private _possibleMoves: number[];
    
    get id(): number {
        return this._id;
    }

    set id(value: number) {
        this._id = value;
    }

    get cell_color(): Color {
        return this._cell_color;
    }

    set cell_color(value: Color) {
        this._cell_color = value;
    }

    get piece(): Piece {
        return this._piece;
    }

    set piece(value: Piece) {
        this._piece = value;
    }

    get possibleMoves(): number[] {
        return this._possibleMoves;
    }

    set possibleMoves(value: number[]) {
        this._possibleMoves = value;
    }

    constructor( id: number, cell_color: Color , piece: Piece, possibleMoves: number[]) {
        this._id = id;
        this._cell_color = cell_color;
        this._piece = piece;
        this._possibleMoves = possibleMoves;
    }
}

import {Color} from './color';
import {Piece} from './piece';

/**
 * Klasa służy do wyświetlania komórek planszy
 * */
export class Cell {
    /** Id komórki*/
    private _id: number;
    /** Color komórki*/
    private _cell_color: Color;
    /** Figurka stojąca na tej komórce*/
    private _piece: Piece;
    /** Możliwe ruchy które są dostępne z komórki*/
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

    /** @ignore */
    constructor( id: number, cell_color: Color , piece: Piece, possibleMoves: number[]) {
        this._id = id;
        this._cell_color = cell_color;
        this._piece = piece;
        this._possibleMoves = possibleMoves;
    }
}

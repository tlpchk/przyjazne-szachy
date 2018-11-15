import {Color} from './color';
import {Piece} from './piece';

export class Cell {
    constructor( id: number, cell_collor: Color , piece: Piece, possibleMoves: number[]) {
        this.id = id;
        this.cell_color = cell_collor;
        this.piece = piece;
        this.possibleMoves = possibleMoves;
    }
    id: number;
    cell_color: Color;
    piece: Piece;
    possibleMoves: number[];
}

import {Color} from './color';
import {Piece} from './piece';

export class Cell {
    constructor( id: number, cell_collor: Color , piece: Piece) {
        this.id = id;
        this.cell_color = cell_collor;
        this.piece = piece;
    }

    id: number;
    cell_color: Color;
    piece: Piece;
}

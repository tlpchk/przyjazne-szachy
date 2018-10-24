import {Piece} from './piece';

export enum Color {
    white = 'white',
    black = 'black'
}


export class Cell {
    constructor( id: number, cell_collor: Color , piece: Piece) {
        this.id = id;
        this.cell_color = cell_collor;

    }

    id: number;
    cell_color: Color;
}

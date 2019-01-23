import {Cell} from './cell';

/** Klasa zawierająca informację o ruchu*/
export class Move {
    /** Komórka źródłowa*/
    srcCell: Cell;
    /** Komórka docelowa*/
    destCell: Cell;

    /** @ignore*/
    constructor() {
        this.srcCell = null;
        this.destCell = null;
    }
}

import {PieceType} from "../_models/piece";
import {Color} from "../_models/color";
import {PositionDTO} from "./positionDTO";


/** @ignore*/
export class PieceDTO {
    /** */
    public row: number;
    /** */
    public column: number;
    /** */
    public type: PieceType;
    /** */
    public color: Color;
    /** */
    public possibleMoves: PositionDTO[];

    /** @ignore*/
    constructor(row: number, column: number, type: PieceType, color: Color, possibleMoves: PositionDTO[]) {
        this.row = row;
        this.column = column;
        this.type = type;
        this.color = color;
        this.possibleMoves = possibleMoves;
    }
}

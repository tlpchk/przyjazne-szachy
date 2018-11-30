import {PieceType} from "./piece";
import {Color} from "./color";
import {PositionDTO} from "./positionDTO";

export class PieceDTO {

    constructor(public row: number,
                public column: number,
                public type: PieceType,
                public color: Color,
                public possibleMoves: PositionDTO[]) {
    }
}

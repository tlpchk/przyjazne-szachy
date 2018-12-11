import {PositionDTO} from "./positionDTO";
import {PieceType} from "./piece";
import {Color} from "./color";

export class ChangeDTO {
    constructor(public location: PositionDTO,
                public type: PieceType,
                public color: Color) {
    }
}
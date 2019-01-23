import {PositionDTO} from "./positionDTO";
import {PieceType} from "../_models/piece";
import {Color} from "../_models/color";

/** @ignore*/
export class ChangeDTO {
    /** */
    public location: PositionDTO;
    /** */
    public type: PieceType;
    /** */
    public color: Color;

    /** @ignore*/
    constructor(location: PositionDTO, type: PieceType, color: Color) {
        this.location = location;
        this.type = type;
        this.color = color;
    }
}
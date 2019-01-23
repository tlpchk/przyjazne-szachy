import {PieceType} from "../_models/piece";

/** @ignore*/
export class PromotionDTO {
    /** */
    public playerId: number;
    /** */
    public pieceType: PieceType;

    /** @ignore*/
    constructor(playerId: number, pieceType: PieceType) {
        this.playerId = playerId;
        this.pieceType = pieceType;
    }
}
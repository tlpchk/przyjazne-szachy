import {PieceType} from "./piece";

export class PromotionDTO {
    constructor(public playerId: number,
                public pieceType: PieceType) {
    }
}
import {PositionDTO} from "./positionDTO";

export class CreateMoveDTO {
    constructor(public playerId: number,
                public origin: PositionDTO,
                public destination: PositionDTO) {
    }
}
import {PositionDTO} from "./positionDTO";


/** @ignore*/
export class CreateMoveDTO {
    /** */
    public playerId: number;
    /** */
    public origin: PositionDTO;
    /** */
    public destination: PositionDTO;

    /** @ignore */
    constructor(playerId: number, origin: PositionDTO, destination: PositionDTO) {
        this.playerId = playerId;
        this.origin = origin;
        this.destination = destination;
    }
}
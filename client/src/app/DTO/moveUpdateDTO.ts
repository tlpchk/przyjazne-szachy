import {MoveResponseDTO} from "./moveResponseDTO";

/** @ignore*/
export class MoveUpdateDTO {
    /** */
    public updateId: number;
    /** */
    public moveDTO: MoveResponseDTO;

    /** @ignore*/
    constructor(updateId: number, moveDTO: MoveResponseDTO) {
        this.updateId = updateId;
        this.moveDTO = moveDTO;
    }
}
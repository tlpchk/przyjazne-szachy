import {ChangeDTO} from "./changeDTO";


/** @ignore*/
export class MoveResponseDTO {
    /** */
    public wasMoveValid: boolean;
    /** */
    public listOfChanges: ChangeDTO[];

    /** @ignore*/
    constructor(wasMoveValid: boolean, listOfChanges: ChangeDTO[]) {
        this.wasMoveValid = wasMoveValid;
        this.listOfChanges = listOfChanges;
    }
}
import {ChangeDTO} from "./changeDTO";

export class MoveResponseDTO {
    constructor(public wasMoveValid: boolean,
                public listOfChanges: ChangeDTO[]) {
    }
}
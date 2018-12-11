import {MoveResponseDTO} from "./moveResponseDTO";

export class MoveUpdateDTO {
    constructor(public updateId: number,
                public moveDTO: MoveResponseDTO) {
    }
}
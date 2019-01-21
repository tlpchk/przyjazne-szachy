import {Color} from "./color";
import {PlayerType} from "./playerType";

export class CreatePlayerDTO {
    constructor(public username: string,
                public color: Color,
                public playerType: PlayerType) {
    }
}
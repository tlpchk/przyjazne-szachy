import {Color} from "../_models/color";
import {PlayerType} from "../_models/playerType";


/** @ignore*/
export class CreatePlayerDTO {
    /** */
    public username: string;
    /** */
    public color: Color;
    /** */
    public playerType: PlayerType;

    /** @ignore*/
    constructor(username: string, color: Color, playerType: PlayerType) {
        this.username = username;
        this.color = color;
        this.playerType = playerType;
    }
}
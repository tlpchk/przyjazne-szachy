
/** @ignore*/
export enum Result {
    first_player_won = "first_player_won",
    second_player_won = "second_player_won",
    draw = "draw"
}

/** @ignore*/
export class GameInfoDTO {
    /** */
    public lastUpdateId: number;
    /** */
    public myTurn: boolean;
    /** */
    public promotion: boolean;
    /** */
    public gameResult: Result;
    /** */
    public opponent: string;

    /** @ignore */
    constructor(lastUpdateId: number, myTurn: boolean, promotion: boolean, gameResult: Result, opponent: string) {
        this.lastUpdateId = lastUpdateId;
        this.myTurn = myTurn;
        this.promotion = promotion;
        this.gameResult = gameResult;
        this.opponent = opponent;
    }
}
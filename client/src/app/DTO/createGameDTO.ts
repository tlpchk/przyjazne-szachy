
/** @ignore*/
export class CreateGameDTO {
    /** */
    public firstPlayerId: number;
    /** */
    public secondPlayerId: number;
    /** */
    public isRanked: boolean;


    /** @ignore*/
    constructor(firstPlayerId: number, secondPlayerId: number, isRanked: boolean) {
        this.firstPlayerId = firstPlayerId;
        this.secondPlayerId = secondPlayerId;
        this.isRanked = isRanked;
    }
}
export class CreateGameDTO {
    constructor(public firstPlayerId: number,
                public secondPlayerId: number,
                public isRanked: boolean) {
    }
}
export class Player {

    constructor() {}

    id: number;
}

export class GameInfo {

    constructor(){};

    id: number;
    firstPlayer: Player;
    secondPlayer: Player;
    gameStatus: string;
    created: string;

}
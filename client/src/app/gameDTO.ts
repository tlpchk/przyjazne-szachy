export class PlayerDTO {

    constructor() {}

    id: number;
}

export class GameDTO {

    constructor(){};

    id: number;
    player: PlayerDTO;
}
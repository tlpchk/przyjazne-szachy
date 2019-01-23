/** @ignore*/
export class PlayerDTO {

    constructor() {}
    id: number;
}

/** @ignore*/
export class GameDTO {

    constructor(){};

    id: number;
    player: PlayerDTO;
}
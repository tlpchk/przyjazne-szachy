export enum GameState {
    checkmate = "checkmate",
    stalemate = "stalemate",
    game_running = "game_running"
}

export enum Result {
    first_player_won = "first_player_won",
    second_player_won = "second_player_won",
    draw = "draw"
}

export class GameInfoDTO {
    constructor(public lastUpdateId: number,
                public myTurn: boolean,
                public promotion: boolean,
                public gameState: GameState,
                public gameResult: Result,
                public opponent: string) {
    }
}
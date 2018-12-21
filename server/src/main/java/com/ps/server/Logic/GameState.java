package com.ps.server.Logic;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GameState {
    CHECKMATE("CHECKMATE"), STALEMATE("STALEMATE"), GAME_RUNNING("GAME_RUNNING");

    private String gameState;

    GameState(String gameState) {
        this.gameState = gameState;
    }

    @JsonValue
    public String getGameState() {
        return gameState.toLowerCase();
    }
}

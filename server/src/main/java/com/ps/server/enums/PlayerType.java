package com.ps.server.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PlayerType {
    HUMAN("HUMAN"),
    BOT("BOT");

    private String playerTypeString;

    PlayerType(String playerTypeString) {
        this.playerTypeString = playerTypeString;
    }

    @JsonValue
    public String getPlayerTypeString() {
        return playerTypeString.toLowerCase();
    }
}

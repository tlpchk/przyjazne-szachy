package com.ps.server.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Result {
    FIRST_PLAYER_WON("FIRST_PLAYER_WON"), SECOND_PLAYER_WON("SECOND_PLAYER_WON"), DRAW("DRAW");

    private String resultString;

    Result(String resultString) {
        this.resultString = resultString;
    }

    @JsonValue
    public String getResultString() {
        return resultString.toLowerCase();
    }
}
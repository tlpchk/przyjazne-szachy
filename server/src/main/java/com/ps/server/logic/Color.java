package com.ps.server.logic;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Color {
    WHITE("white"), BLACK("black");

    private String colorString;

    Color(String colorString) {
        this.colorString = colorString;
    }

    @JsonValue
    public String getColorString() {
        return colorString;
    }
}

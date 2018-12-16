package com.ps.server.Logic;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Color {
    WHITE("WHITE"), BLACK("BLACK");

    private String colorString;

    Color(String colorString) {
        this.colorString = colorString;
    }

    @JsonValue
    public String getColorString() {
        return colorString;
    }
}

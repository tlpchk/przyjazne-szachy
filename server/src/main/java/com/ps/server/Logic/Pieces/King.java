package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.Color;

import static com.ps.server.Logic.Color.WHITE;

public class King extends ChessPiece {
    private boolean castleRights;

    public King(Color color) {
        super(color);
        castleRights = true;
    }

    @Override
    public String toString() {
        return color == WHITE ? "♚" : "♔";
    }
}

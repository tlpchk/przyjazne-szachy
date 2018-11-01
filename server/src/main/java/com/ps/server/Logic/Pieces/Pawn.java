package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.Color;

import static com.ps.server.Logic.Color.WHITE;

public class Pawn extends ChessPiece {
    private boolean isFirstMove;

    public Pawn(Color color) {
        super(color);
        isFirstMove = true;
    }

    @Override
    public String toString() {
        return color == WHITE ? "♟" : "♙";
    }
}

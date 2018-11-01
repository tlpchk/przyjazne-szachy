package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.Color;

import static com.ps.server.Logic.Color.WHITE;

public class Queen extends ChessPiece {
    public Queen(Color color) {
        super(color);
    }

    @Override
    public String toString() {
        return color == WHITE ? "♛" : "♕";
    }
}

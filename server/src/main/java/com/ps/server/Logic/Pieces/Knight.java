package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.Color;

import static com.ps.server.Logic.Color.WHITE;

public class Knight extends ChessPiece {
    public Knight(Color color) {
        super(color);
    }

    @Override
    public String toString() {
        return color == WHITE ? "♞" : "♘";
    }
}

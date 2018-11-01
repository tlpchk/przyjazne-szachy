package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.Color;
import com.ps.server.Logic.Position;

import static com.ps.server.Logic.Color.WHITE;

public class King extends ChessPiece {
    private boolean castleRights;

    public King(Color color) {
        super(color);
        castleRights = true;
    }

    public boolean checkMove(Position loc, Position dest) {
        int distX = Math.abs(loc.col - dest.col);
        int distY = Math.abs(loc.row - dest.row);
        return (distX + distY == 1) || (distY == 1 && distX == 1);
    }

    @Override
    public String toString() {
        return color == WHITE ? "♚" : "♔";
    }
}

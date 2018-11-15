package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.Color;
import com.ps.server.Logic.Position;

import static com.ps.server.Logic.Color.WHITE;

public class Bishop extends ChessPiece {
    public Bishop(Color color) {
        super(color);
    }

    public boolean checkMove(Position loc, Position dest) {
        int distX = Math.abs(loc.col - dest.col);
        int distY = Math.abs(loc.row - dest.row);
        return distX > 0 && distX == distY;
    }

    @Override
    public String toString() {
        return color == WHITE ? "♝" : "♗";
    }
}

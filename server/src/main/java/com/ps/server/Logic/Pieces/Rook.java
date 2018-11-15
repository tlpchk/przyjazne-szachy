package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.Color;
import com.ps.server.Logic.Position;

import static com.ps.server.Logic.Color.*;

public class Rook extends ChessPiece {
    private boolean castleRights;

    public Rook(Color color) {
        super(color);
        castleRights = true;
    }

    public boolean checkMove(Position loc, Position dest) {
        int distX = Math.abs(loc.col - dest.col);
        int distY = Math.abs(loc.row - dest.row);
        return (distX > 0 && distY == 0) || (distX == 0 && distY > 0);
    }

    @Override
    public String toString() {
        return color == WHITE ? "♜" : "♖";
    }
}
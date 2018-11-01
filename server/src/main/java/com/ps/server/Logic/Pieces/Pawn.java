package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.Color;
import com.ps.server.Logic.Position;

import static com.ps.server.Logic.Color.WHITE;

public class Pawn extends ChessPiece {
    private boolean isFirstMove;

    public Pawn(Color color) {
        super(color);
        isFirstMove = true;
    }

    @Override
    public boolean checkMove(Position loc, Position dest) {
        if(color == WHITE) {
            return loc.row - dest.row == 1 || (isFirstMove && loc.row - dest.row == 2);
        } else {
            return loc.row - dest.row == -1 || (isFirstMove && loc.row - dest.row == -2);
        }
    }

    @Override
    public boolean checkCapture(Position loc, Position dest, ChessPiece opponentPiece) {
        if(color == WHITE) {
            return loc.row - dest.row == -1 && Math.abs(loc.col - dest.col) == 1;
        } else {
            return loc.row - dest.row == 1 && Math.abs(loc.col - dest.col) == 1;
        }
    }

    @Override
    public String toString() {
        return color == WHITE ? "♟" : "♙";
    }
}

package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.Color;
import com.ps.server.Logic.Position;

public abstract class ChessPiece {
    final public Color color;

    ChessPiece(Color color) {
        this.color = color;
    }

    public abstract boolean checkMove(Position loc, Position dest);
    public boolean checkCapture(Position loc, Position dest, ChessPiece opponentPiece) {
        return checkMove(loc, dest);
    }
}

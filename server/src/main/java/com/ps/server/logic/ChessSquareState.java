package com.ps.server.logic;

import com.ps.server.logic.pieces.Piece;

public class ChessSquareState {
    public final State state;
    private Piece piece;

    public ChessSquareState(Piece piece) {
        if(piece == null) {
            state = State.EMPTY;
        } else {
            this.piece = piece;
            state = State.OCCUPIED;
        }
    }

    public Piece getPiece() {
        return piece;
    }

    public enum State {
        EMPTY, OCCUPIED
    }
}

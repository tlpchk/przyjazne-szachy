package com.ps.server.Logic;

import com.ps.server.Logic.Pieces.Piece;

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

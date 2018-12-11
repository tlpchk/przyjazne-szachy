package com.ps.server.Logic;

import com.ps.server.Logic.Pieces.Piece;

public class ChessSquareState {
    /** state of the chess square either EMPTY or OCCUPIED */
    public final State state;
    private Piece piece;

    /**
     * Class constructor for ChessSquareState
     * @param piece instance of class Piece, the piece that stands on this chess square
     *              if null then state = empty
     *              otherwise occupied
     */
    public ChessSquareState(Piece piece) {
        if(piece == null) {
            state = State.EMPTY;
        } else {
            this.piece = piece;
            state = State.OCCUPIED;
        }
    }

    /**
     * @return instance of class Piece, the piece that stands on this chess square
     *              if empty then returns null
     */
    public Piece getPiece() {
        return piece;
    }

    public enum State {
        EMPTY, OCCUPIED
    }
}

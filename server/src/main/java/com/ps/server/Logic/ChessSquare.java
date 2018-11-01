package com.ps.server.Logic;

import com.ps.server.Logic.Pieces.ChessPiece;

class ChessSquare {
    ChessPiece piece;

    public ChessSquare(ChessPiece piece) {
        this.piece = piece;
    }

    @Override
    public String toString() {
        return piece == null ? "_" : piece.toString();
    }

}

package com.ps.server.Logic;

import com.ps.server.Logic.Pieces.Piece;

public class Move {
    final public Piece.PieceType pieceType;
    final public Color pieceColor;
    final public Position loc;
    final public Position dest;
    final public MoveType type;

    public Move(Piece.PieceType pieceType, Color pieceColor, Position loc, Position dest, MoveType type) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
        this.loc = loc;
        this.dest = dest;
        this.type = type;
    }

    public enum MoveType {
        NORMAL, CASTLE, EN_PASSANT, PROMOTION, LONG_PAWN_MOVE
    }
}

package com.ps.server.Logic;

import com.ps.server.Logic.Pieces.Piece;

public class Move {
    final public Piece.PieceType pieceType;
    final public Color pieceColor;
    final public Position loc;
    final public Position dest;
    final public MoveType type;
    private Piece.PieceType promoteTo;

    /**
     * Class constructor
     * @param pieceType the type of piece making the move (KING, QUEEN, ROOK, KNIGHT, BISHOP, PAWN)
     * @param pieceColor the color of piece making move (WHITE, BLACK)
     * @param loc location of the piece making move (before making move)
     * @param dest destination of the move, new location for the moving piece
     *             (for king making castle this will be the position of the rook)
     * @param type move type, one of the following: NORMAL (normal move or regular capture),
     *             SHORT_CASTLE, EN_PASSANT, PROMOTION, LONG_PAWN_MOVE, LONG_CASTLE
     */
    public Move(Piece.PieceType pieceType, Color pieceColor, Position loc, Position dest, MoveType type) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
        this.loc = loc;
        this.dest = dest;
        this.type = type;
        if(type == MoveType.PROMOTION)
            setPromoteTo(Piece.PieceType.QUEEN);
    }

    /**
     * Sets promoteTo if the move is a PROMOTION, otherwise throws exception
     * @param promoteTo the type of piece that pawn should be promoted to (QUEEN, ROOK, KNIGHT, BISHOP),
     *                  default value will be QUEEN
     */
    public void setPromoteTo(Piece.PieceType promoteTo) {
        if(type == MoveType.PROMOTION) {
            this.promoteTo = promoteTo;
        } else throw new RuntimeException("setting promote to in not PROMOTION move");
    }

    /**
     * Gets promoteTo if the move is a PROMOTION, otherwise throws exception
     * @return the type of piece that pawn should be promoted to (QUEEN, ROOK, KNIGHT, BISHOP),
     *                  default value will be QUEEN
     */
    public Piece.PieceType getPromoteTo() {
        if(type == MoveType.PROMOTION) {
            return promoteTo;
        } else throw new RuntimeException("getting promote to in not PROMOTION move");
    }

    public enum MoveType {
        NORMAL, SHORT_CASTLE, EN_PASSANT, PROMOTION, LONG_PAWN_MOVE, LONG_CASTLE
    }
}
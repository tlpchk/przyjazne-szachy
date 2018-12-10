package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.*;

import java.util.ArrayList;
import java.util.List;

import static com.ps.server.Logic.Color.WHITE;
import static com.ps.server.Logic.Pieces.Piece.PieceType.BISHOP;

public class Bishop extends StraightMovingPieces {

    /**
     * Class constructor.
     * @param position on board, where the piece stands (should be in bounds row: 0-7, column: 0-7)
     * @param color specifies the color of chess piece (WHITE or BLACK)
     */
    public Bishop(Color color, Position position) {
        super(color, BISHOP, position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return color == WHITE ? "♝" : "♗";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Move> semiLegalMoves() {
        List<Move> legalMoves = new ArrayList<>();
        legalMoves.addAll(generateMovesInDirection(1, 1));
        legalMoves.addAll(generateMovesInDirection(1, -1));
        legalMoves.addAll(generateMovesInDirection(-1, 1));
        legalMoves.addAll(generateMovesInDirection(-1, -1));
        return legalMoves;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkIfCanCaptureKingOn(Position kingsPosition) {
        int distX = Math.abs(position.col - kingsPosition.col);
        int distY = Math.abs(position.row - kingsPosition.row);
        return (distX > 0 && distX == distY) && !checkIfSomethingBetween(kingsPosition);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Piece copy() {
        return new Bishop(color, position);
    }
}

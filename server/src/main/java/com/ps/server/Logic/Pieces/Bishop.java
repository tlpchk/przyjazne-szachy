package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.*;

import java.util.ArrayList;
import java.util.List;

import static com.ps.server.Logic.Color.WHITE;
import static com.ps.server.Logic.Pieces.Piece.PieceType.BISHOP;

public class Bishop extends StraightMovingPieces {

    public Bishop(Color color, Position position) {
        super(color, BISHOP, position);
    }

    @Override
    public String toString() {
        return color == WHITE ? "♝" : "♗";
    }

    @Override
    public List<Move> semiLegalMoves() {
        List<Move> legalMoves = new ArrayList<>();
        legalMoves.addAll(generateMovesInDirection(1, 1));
        legalMoves.addAll(generateMovesInDirection(1, -1));
        legalMoves.addAll(generateMovesInDirection(-1, 1));
        legalMoves.addAll(generateMovesInDirection(-1, -1));
        return legalMoves;
    }

    @Override
    public boolean checkIfCanCaptureKingOn(Position kingsPosition) {
        int distX = Math.abs(position.col - kingsPosition.col);
        int distY = Math.abs(position.row - kingsPosition.row);
        return (distX > 0 && distX == distY) && !checkIfSomethingBetween(kingsPosition);
    }

    @Override
    public Piece copy() {
        return new Bishop(color, position);
    }
}

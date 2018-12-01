package com.ps.server.logic.pieces;

import com.ps.server.logic.Color;
import com.ps.server.logic.Move;
import com.ps.server.logic.Position;

import java.util.ArrayList;
import java.util.List;

import static com.ps.server.logic.Color.WHITE;
import static com.ps.server.logic.pieces.Piece.PieceType.QUEEN;

public class Queen extends StraightMovingPieces {
    public Queen(Color color, Position position) {
        super(color, QUEEN, position);
    }

    @Override
    public List<Move> semiLegalMoves() {
        List<Move> legalMoves = new ArrayList<>();
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                legalMoves.addAll(generateMovesInDirection(i, j));
            }
        }
        return legalMoves;
    }

    @Override
    public boolean checkIfCanCaptureKingOn(Position kingsPosition) {
        int distX = Math.abs(position.column - kingsPosition.column);
        int distY = Math.abs(position.row - kingsPosition.row);
        return ((distX > 0 && distY == 0) || (distX == 0 && distY > 0) || (distX > 0 && distX == distY))
                && !checkIfSomethingBetween(kingsPosition);
    }

    @Override
    public Piece copy() {
        return new Queen(color, position);
    }

    @Override
    public String toString() {
        return color == WHITE ? "♛" : "♕";
    }
}

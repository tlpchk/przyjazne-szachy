package com.ps.server.logic.pieces;

import com.ps.server.logic.Color;
import com.ps.server.logic.Move;
import com.ps.server.logic.Position;

import java.util.ArrayList;
import java.util.List;

import static com.ps.server.logic.Color.*;
import static com.ps.server.logic.pieces.Piece.PieceType.ROOK;

public class Rook extends StraightMovingPieces {
    private boolean castleRights;

    public Rook(Color color, Position position) {
        super(color, ROOK, position);
        castleRights = true;
    }

    @Override
    public String toString() {
        return color == WHITE ? "♜" : "♖";
    }

    @Override
    public List<Move> semiLegalMoves() {
        List<Move> legalMoves = new ArrayList<>();
        legalMoves.addAll(generateMovesInDirection(0, 1));
        legalMoves.addAll(generateMovesInDirection(0, -1));
        legalMoves.addAll(generateMovesInDirection(1, 0));
        legalMoves.addAll(generateMovesInDirection(-1, 0));
        return legalMoves;
    }

    @Override
    public boolean checkIfCanCaptureKingOn(Position kingsPosition) {
        int distX = Math.abs(position.column - kingsPosition.column);
        int distY = Math.abs(position.row - kingsPosition.row);
        return ((distX > 0 && distY == 0) || (distX == 0 && distY > 0)) && !checkIfSomethingBetween(kingsPosition);
    }

    @Override
    public Piece copy() {
        return new Rook(color, position);
    }

    @Override
    public void move(Position destination) {
        castleRights = false;
        super.move(destination);
    }

    @Override
    boolean hasCastleRights() {
        return castleRights;
    }
}
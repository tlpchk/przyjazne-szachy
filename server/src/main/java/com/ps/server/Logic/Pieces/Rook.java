package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.Color;
import com.ps.server.Logic.Move;
import com.ps.server.Logic.Position;

import java.util.ArrayList;
import java.util.List;

import static com.ps.server.Logic.Color.*;
import static com.ps.server.Logic.Pieces.Piece.PieceType.ROOK;

public class Rook extends StraightMovingPieces {
    private boolean castleRights;

    /**
     * Class constructor.
     * @param position on board, where the piece stands (should be in bounds row: 0-7, column: 0-7)
     * @param color specifies the color of chess piece (WHITE or BLACK)
     */
    public Rook(Color color, Position position) {
        super(color, ROOK, position);
        castleRights = true;
    }

    Rook(Color color, Position position, boolean castleRights) {
        super(color, ROOK, position);
        this.castleRights = castleRights;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return color == WHITE ? "♜" : "♖";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Move> semiLegalMoves() {
        List<Move> legalMoves = new ArrayList<>();
        legalMoves.addAll(generateMovesInDirection(0, 1));
        legalMoves.addAll(generateMovesInDirection(0, -1));
        legalMoves.addAll(generateMovesInDirection(1, 0));
        legalMoves.addAll(generateMovesInDirection(-1, 0));
        return legalMoves;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkIfCanCaptureKingOn(Position kingsPosition) {
        int distX = Math.abs(position.col - kingsPosition.col);
        int distY = Math.abs(position.row - kingsPosition.row);
        return ((distX > 0 && distY == 0) || (distX == 0 && distY > 0)) && !checkIfSomethingBetween(kingsPosition);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Piece copy() {
        return new Rook(color, position, castleRights);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(Position destination) {
        castleRights = false;
        super.move(destination);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    boolean hasCastleRights() {
        return castleRights;
    }
}
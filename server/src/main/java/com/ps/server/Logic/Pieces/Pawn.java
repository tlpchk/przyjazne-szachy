package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.Color;
import com.ps.server.Logic.Move;
import com.ps.server.Logic.Position;

import java.util.ArrayList;
import java.util.List;

import static com.ps.server.Logic.Color.WHITE;
import static com.ps.server.Logic.Move.*;
import static com.ps.server.Logic.Move.MoveType.*;
import static com.ps.server.Logic.Pieces.Piece.PieceType.PAWN;

public class Pawn extends Piece {
    private boolean isFirstMove;
    private Position enPassant;

    /**
     * Class constructor.
     * @param position on board, where the piece stands (should be in bounds row: 0-7, column: 0-7)
     * @param color specifies the color of chess piece (WHITE or BLACK)
     */
    public Pawn(Color color, Position position) {
        super(color, PAWN, position);
        isFirstMove = true;
    }

    Pawn(Color color, Position position, boolean isFirstMove, Position enPassant) {
        super(color, PAWN, position);
        this.isFirstMove = isFirstMove;
        this.enPassant = enPassant;
    }

    private List<Move> normalLegalMoves() {
        List<Move> normalLegalMoves = new ArrayList<>();
        int newRow = (color == WHITE) ? position.row - 1 : position.row + 1;
        if(board.ifEmpty(new Position(newRow, position.col))) {
            MoveType type = (newRow == 0 || newRow == 7) ? PROMOTION : NORMAL;
            normalLegalMoves.add(new Move(this.type, color, position, new Position(newRow, position.col), type));
            if(isFirstMove) {
                newRow = (color == WHITE) ? newRow -1 : newRow + 1;
                if(board.ifEmpty(new Position(newRow, position.col))) {
                    Move move = new Move(this.type, color, position, new Position(newRow, position.col), LONG_PAWN_MOVE);
                    normalLegalMoves.add(move);
                }
            }
        }
        return normalLegalMoves;
    }

    private Move createLegalCapture(int row, int column) {
        if(board.ifOccupiedByOpponent(new Position(row, column), color)) {
            MoveType type = (row == 0 || row == 7) ? PROMOTION : NORMAL;
            return new Move(this.type, color, position, new Position(row, column), type);
        }
        return null;
    }

    private List<Move> legalCaptures() {
        List<Move> legalCaptures = new ArrayList<>();
        int newRow = (color == WHITE) ? position.row - 1 : position.row + 1;
        Move move = createLegalCapture(newRow, position.col + 1);
        if(move != null) {legalCaptures.add(move);}
        move = createLegalCapture(newRow, position.col - 1);
        if(move != null) {legalCaptures.add(move);}
        if(enPassant != null) {
            legalMoves.add(new Move(this.type, color, position, new Position( newRow, enPassant.col), EN_PASSANT));
        }
        return legalCaptures;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Move> semiLegalMoves() {
        List<Move> legalMoves = new ArrayList<>();
        legalMoves.addAll(normalLegalMoves());
        legalMoves.addAll(legalCaptures());
        return legalMoves;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkIfCanCaptureKingOn(Position kingsPosition) {
        if(color == WHITE) {
            return position.row - kingsPosition.row == 1 && Math.abs(position.col - kingsPosition.col) == 1;
        } else {
            return position.row - kingsPosition.row == -1 && Math.abs(position.col - kingsPosition.col) == 1;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Piece copy() {
        return new Pawn(color, position, isFirstMove, enPassant);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(Position destination) {
        isFirstMove = false;
        super.move(destination);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        super.update();
        enPassant = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void giveEnPassantRights(Position position) {
        enPassant = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return color == WHITE ? "♟" : "♙";
    }

}

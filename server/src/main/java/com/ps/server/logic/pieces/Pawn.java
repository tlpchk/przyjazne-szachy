package com.ps.server.logic.pieces;

import com.ps.server.logic.Color;
import com.ps.server.logic.Move;
import com.ps.server.logic.Position;

import java.util.ArrayList;
import java.util.List;

import static com.ps.server.logic.Color.WHITE;
import static com.ps.server.logic.Move.*;
import static com.ps.server.logic.Move.MoveType.*;
import static com.ps.server.logic.pieces.Piece.PieceType.PAWN;

public class Pawn extends Piece {
    private boolean isFirstMove;
    private Position enPassant;

    public Pawn(Color color, Position position) {
        super(color, PAWN, position);
        isFirstMove = true;
    }

    private List<Move> normalLegalMoves() {
        List<Move> normalLegalMoves = new ArrayList<>();
        int newRow = (color == WHITE) ? position.row - 1 : position.row + 1;
        if(board.ifEmpty(new Position(newRow, position.column))) {
            MoveType type = (newRow == 0 || newRow == 7) ? PROMOTION : NORMAL;
            normalLegalMoves.add(new Move(this.type, color, position, new Position(newRow, position.column), type));
            if(isFirstMove) {
                newRow = (color == WHITE) ? newRow -1 : newRow + 1;
                if(board.ifEmpty(new Position(newRow, position.column))) {
                    Move move = new Move(this.type, color, position, new Position(newRow, position.column), LONG_PAWN_MOVE);
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
        Move move = createLegalCapture(newRow, position.column + 1);
        if(move != null) {legalCaptures.add(move);}
        move = createLegalCapture(newRow, position.column - 1);
        if(move != null) {legalCaptures.add(move);}
        if(enPassant != null) {
            legalMoves.add(new Move(this.type, color, position, new Position( newRow, enPassant.column), EN_PASSANT));
        }
        return legalCaptures;
    }

    @Override
    public List<Move> semiLegalMoves() {
        List<Move> legalMoves = new ArrayList<>();
        legalMoves.addAll(normalLegalMoves());
        legalMoves.addAll(legalCaptures());
        return legalMoves;
    }

    @Override
    public boolean checkIfCanCaptureKingOn(Position kingsPosition) {
        if(color == WHITE) {
            return position.row - kingsPosition.row == -1 && Math.abs(position.column - kingsPosition.column) == 1;
        } else {
            return position.row - kingsPosition.row == 1 && Math.abs(position.column - kingsPosition.column) == 1;
        }
    }

    @Override
    public Piece copy() {
        return new Pawn(color, position);
    }

    @Override
    public void move(Position destination) {
        isFirstMove = false;
        super.move(destination);
    }

    @Override
    public void legalMoves() {
        super.legalMoves();
        enPassant = null;
    }

    @Override
    public void giveEnPassantRights(Position position) {
        enPassant = position;
    }

    @Override
    public String toString() {
        return color == WHITE ? "♟" : "♙";
    }

}

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

    public Pawn(Color color, Position position) {
        super(color, PAWN, position);
        isFirstMove = true;
    }

    List<Move> normalLegalMoves(Piece[][] board) {
        List<Move> normalLegalMoves = new ArrayList<>();
        int newRow = (color == WHITE) ? position.row - 1 : position.row + 1;
        if(board[newRow][position.col] == null) {
            MoveType type = (newRow == 0 || newRow == 8) ? PROMOTION : NORMAL;
            normalLegalMoves.add(new Move(this.type, color, position, new Position(newRow, position.col), type));
            if(isFirstMove) {
                newRow = (color == WHITE) ? newRow -1 : newRow + 1;
                if(board[newRow][position.col] == null) {
                    Move move = new Move(this.type, color, position, new Position(newRow, position.col), LONG_PAWN_MOVE);
                    normalLegalMoves.add(move);
                }
            }
        }
        return normalLegalMoves;
    }

    Move createLegalCapture(int row, int column, Piece[][] board) {
        Piece captured = board[row][column];
        if(captured != null && captured.color != color) {
            MoveType type = (row == 0 || row == 8) ? PROMOTION : NORMAL;
            return new Move(this.type, color, position, new Position(row, column), type);
        }
        return null;
    }

    List<Move> legalCaptures(Piece[][] board) {
        List<Move> legalCaptures = new ArrayList<>();
        int newRow = (color == WHITE) ? position.row - 1 : position.row + 1;
        Move move = createLegalCapture(newRow, position.col + 1, board);
        if(move != null) {legalCaptures.add(move);}
        move = createLegalCapture(newRow, position.col - 1, board);
        if(move != null) {legalCaptures.add(move);}
        if(enPassant != null) {
            legalMoves.add(new Move(this.type, color, position, new Position( newRow, enPassant.col), EN_PASSANT));
        }
        return legalCaptures;
    }

    @Override
    public List<Move> semiLegalMoves() {
        List<Move> legalMoves = new ArrayList<>();
        Piece[][] board = this.board.getBoard();
        legalMoves.addAll(normalLegalMoves(board));
        legalMoves.addAll(legalCaptures(board));
        return legalMoves;
    }

    @Override
    public boolean checkIfCanCaptureKingOn(Position kingsPosition) {
        Position dest = kingsPosition;
        if(color == WHITE) {
            return position.row - dest.row == -1 && Math.abs(position.col - dest.col) == 1;
        } else {
            return position.row - dest.row == 1 && Math.abs(position.col - dest.col) == 1;
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

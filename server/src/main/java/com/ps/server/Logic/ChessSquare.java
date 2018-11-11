package com.ps.server.Logic;

import java.util.ArrayList;
import java.util.List;
import com.ps.server.Logic.Pieces.ChessPiece;
import static com.ps.server.Logic.Board.*;

class ChessSquare {
    ChessPiece piece;
    final Position position;
    private final Board board;

    private List<ChessSquare> movesTo;
    private List<ChessSquare> movesFrom;

    public ChessSquare(ChessPiece piece, Position position, Board board) {
        this.piece = piece;
        this.position = position;
        this.board = board;
        movesFrom = new ArrayList<>();
        movesTo = new ArrayList<>();
    }

    public ChessPiece getPiece() {
        return piece;
    }

    @Override
    public String toString() {
        return piece == null ? "_" : piece.toString();
    }

    void addMovesFrom(ChessSquare loc) {
        movesFrom.add(loc);
    }

    void removeMoveFrom(ChessSquare loc) {
        movesFrom.remove(loc);
    }

    boolean checkMoveTo(Position dest, ChessPiece opponentPiece) {
        if(opponentPiece != null) {
            if (opponentPiece.color == piece.color) return false; //TODO:: castle
            return  piece.checkCapture(position, dest, opponentPiece);
        } else {
            return piece.checkMove(position, dest);
        }

    }

    void makeMoveList() {
        if(piece == null) return;
        ChessSquare[][] board = this.board.getBoard();
        for(int row = 0; row < ROW_NUM; row++) {
            for(int col = 0; col < COL_NUM; col++) {
                ChessSquare destSquare = board[row][col];
                if(checkMoveTo(destSquare.position, destSquare.getPiece())) {
                    movesTo.add(destSquare);
                    destSquare.addMovesFrom(this);
                }
            }
        }
    }

    boolean makeMove(ChessSquare loc, Color color) {
        if(!movesTo.contains(loc) || color != piece.color) return false;
        //TODO:: if PAWN isFirstMove = false && ROOK, KING castleRights
        //TODO:: en passant and castle
        loc.piece = piece;
        movesTo.forEach(e -> e.removeMoveFrom(this));
        makeMoveList();
        piece = null;
        return true;
    }

}

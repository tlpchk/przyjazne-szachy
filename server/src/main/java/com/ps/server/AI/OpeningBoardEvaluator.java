package com.ps.server.AI;

import com.ps.server.Logic.Board;
import com.ps.server.Logic.ChessSquareState;
import com.ps.server.Logic.Color;
import com.ps.server.Logic.Pieces.Piece;
import com.ps.server.Logic.Position;

public class OpeningBoardEvaluator extends StandardBoardEvaluator {
    static boolean boardFirstMoves[][];

    public OpeningBoardEvaluator() {
        boardFirstMoves = new boolean[Board.ROW_NUM][];

        for(int i = 0; i < Board.ROW_NUM; i++) {
            boardFirstMoves[i] = new boolean[Board.COL_NUM];
        }

        for(int i = 0; i < Board.ROW_NUM; i++) {
            for(int j = 0; j < Board.COL_NUM; j++) {
                boardFirstMoves[i][j] = true;
            }
        }
    }

    @Override
    protected int scorePlayer(final Board board, final Color color) {
        return scorePieces(board, color)
                + firstMove(board, color)
                + pawnsInCenter(board, color)
                + castledKing(board, color)
                + rooksConnected(board, color);
        //TODO add score for developing pieces, castling, moving central pawns, connecting rooks, subtract score for too early queen moves
    }

    //TODO implement
    private int rooksConnected(Board board, Color color) {
        return 0;
    }

    //TODO implement
    private int castledKing(Board board, Color color) {
        return 0;
    }

    //TODO implement
    private int pawnsInCenter(final Board board, final Color color) {
        return 0;
    }

    //TODO test, think about returned result
    private int firstMove(final Board board, final Color color) {
        if(color == Color.WHITE) {
            for(int j = 0; j < Board.COL_NUM; j++) {
                if(board.getChessSquareState(new Position(7, j)).state == ChessSquareState.State.EMPTY
                        && boardFirstMoves[7][j] == true) {
                    boardFirstMoves[7][j] = false;
                    return 40;
                }
            }
        }
        else {
            for(int j = 0; j < Board.COL_NUM; j++) {
                if(board.getChessSquareState(new Position(0, j)).state == ChessSquareState.State.EMPTY
                        && boardFirstMoves[0][j] == true) {
                    boardFirstMoves[0][j] = false;
                    return -40;
                }
            }
        }
        return 0;
    }

    @SuppressWarnings("Duplicates")
    @Override
    protected int pieceValue(Piece.PieceType piece) {
        if(piece == Piece.PieceType.PAWN) {
            return 100;
        }
        else if(piece == Piece.PieceType.KNIGHT) {
            return 300;
        }
        else if(piece == Piece.PieceType.BISHOP) {
            return 300;
        }
        else if(piece == Piece.PieceType.ROOK) {
            return 500;
        }
        else if(piece == Piece.PieceType.QUEEN) {
            return 900;
        }
        else if(piece == Piece.PieceType.KING) {
            return 10000;
        }
        else {
            return 0;
        }
    }
}

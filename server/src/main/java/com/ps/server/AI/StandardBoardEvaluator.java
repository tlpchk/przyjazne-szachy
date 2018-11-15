package com.ps.server.AI;

import com.ps.server.Logic.Board;
import com.ps.server.Logic.Color;
import com.ps.server.Logic.Pieces.*;

public class StandardBoardEvaluator implements BoardEvaluator {
    @Override
    public int evaluate(Board board) {
        return scorePlayer(board, Color.WHITE) - scorePlayer(board, Color.BLACK);
    }

    private int scorePlayer(final Board board, final Color color) {
        int pieceValueScore = 0;
        Piece[][] chessBoard = board.getBoard();

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(chessBoard[i][j] == null)
                    continue;
                if(chessBoard[i][j].color == color) {
                    pieceValueScore += pieceValue(chessBoard[i][j].type);
                }
            }
        }

        return pieceValueScore;
    }

    private int pieceValue(Piece.PieceType piece) {
        if(piece == Piece.PieceType.PAWN) {
            return 1;
        }
        else if(piece == Piece.PieceType.KNIGHT) {
            return 3;
        }
        else if(piece == Piece.PieceType.BISHOP) {
            return 3;
        }
        else if(piece == Piece.PieceType.ROOK) {
            return 5;
        }
        else if(piece == Piece.PieceType.QUEEN) {
            return 9;
        }
        else if(piece == Piece.PieceType.KING) {
            return 100;
        }
        else {
            return 0;
        }
    }
}

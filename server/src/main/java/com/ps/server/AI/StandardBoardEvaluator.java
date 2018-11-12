package com.ps.server.AI;

import com.ps.server.Logic.Board;
import com.ps.server.Logic.ChessSquare;
import com.ps.server.Logic.Color;
import com.ps.server.Logic.Pieces.*;

public class StandardBoardEvaluator implements BoardEvaluator {
    @Override
    public int evaluate(Board board) {
        return scorePlayer(board, Color.WHITE) - scorePlayer(board, Color.BLACK);
    }

    private int scorePlayer(final Board board, final Color color) {
        int pieceValueScore = 0;
        ChessSquare[][] chessBoard = board.getBoard();

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(chessBoard[i][j].getPiece() == null)
                    continue;
                if(chessBoard[i][j].getPiece().color == color) {
                    pieceValueScore += pieceValue(chessBoard[i][j].getPiece());
                }
            }
        }

        return pieceValueScore;
    }

    private int pieceValue(ChessPiece piece) {
        if(piece.getClass() == Pawn.class) {
            return 1;
        }
        else if(piece.getClass() == Knight.class) {
            return 3;
        }
        else if(piece.getClass() == Bishop.class) {
            return 3;
        }
        else if(piece.getClass() == Rook.class) {
            return 5;
        }
        else if(piece.getClass() == Queen.class) {
            return 9;
        }
        else if(piece.getClass() == King.class) {
            return 100;
        }
        else {
            return 0;
        }
    }
}

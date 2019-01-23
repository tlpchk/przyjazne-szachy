package com.ps.server.AI;

import com.ps.server.Logic.Board;
import com.ps.server.Logic.Color;
import com.ps.server.Logic.Pieces.*;

/**
 * Base class of evaluating positions
 */
public class StandardBoardEvaluator implements BoardEvaluator {
    /**
     *
     * @param board Board instance
     * @return evaluation of position
     */
    @Override
    public int evaluate(Board board) {
        return scorePlayer(board, Color.WHITE) - scorePlayer(board, Color.BLACK);
    }

    /**
     * Calls methods to score points for single player
     * @param board Board instance
     * @param color player color
     * @return score for individual player
     */
    protected int scorePlayer(final Board board, final Color color) {
        return scorePieces(board, color);
    }

    /**
     * Calculates sum of pieces values
     * @param board Board instance
     * @param color player color
     * @return total pieces value
     */
    protected int scorePieces(final Board board, final Color color) {
        int pieceValueScore = 0;
        Piece[][] chessBoard = board.getBoard();

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(chessBoard[i][j] == null)
                    continue;
                if(chessBoard[i][j].color == color) {
                    pieceValueScore += pieceValue(chessBoard[i][j].getType());
                }
            }
        }

        return pieceValueScore;
    }

    /**
     * Determines heuristic value of single piece
     * @param piece single piece
     * @return score for given piece
     */
    protected int pieceValue(Piece.PieceType piece) {
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

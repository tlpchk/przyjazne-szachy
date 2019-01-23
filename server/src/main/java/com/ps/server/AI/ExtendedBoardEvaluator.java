package com.ps.server.AI;

import com.ps.server.Logic.Board;
import com.ps.server.Logic.Color;
import com.ps.server.Logic.Pieces.Piece;

/**
 * Evaluator dedicated middle and game in chess match
 */
public class ExtendedBoardEvaluator extends StandardBoardEvaluator {
    /**
     * Calculates sum of player points based on various factors
     * @param board Board instance
     * @param color player color
     * @return total points gained by player
     */
    @Override
    protected int scorePlayer(final Board board, final Color color) {
        return scorePieces(board, color) + mobility(board, color);
        //TODO add score for checks, checkmate, castle, pawn structure, passed pawns, ...
    }

    /**
     * Determines heuristic value of single piece
     * @see StandardBoardEvaluator
     * @param piece single piece
     * @return
     */
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

    /**
     * Score for pieces activity
     * @param board Board instance
     * @param color player color
     * @return 1 point for each move available to player
     */
    private int mobility(final Board board, final Color color) {
        board.updateGame(color);
        return board.getLegalMoves(color).size();
    }
}

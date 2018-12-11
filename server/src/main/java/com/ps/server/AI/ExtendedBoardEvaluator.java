package com.ps.server.AI;

import com.ps.server.Logic.Board;
import com.ps.server.Logic.Color;
import com.ps.server.Logic.Move;
import com.ps.server.Logic.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class ExtendedBoardEvaluator extends StandardBoardEvaluator {
    @Override
    protected int scorePlayer(final Board board, final Color color) {
        return scorePieces(board, color) + mobility(board, color);
        //TODO add score for checks, checkmate, castle, pawn structure, passed pawns, ...
    }

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

    private int mobility(final Board board, final Color color) {
        board.updateGame(color);
        return board.getLegalMoves(color).size();
    }
}

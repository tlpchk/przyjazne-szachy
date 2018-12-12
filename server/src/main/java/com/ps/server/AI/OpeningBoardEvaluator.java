package com.ps.server.AI;

import com.ps.server.Logic.Board;
import com.ps.server.Logic.ChessSquareState;
import com.ps.server.Logic.Color;
import com.ps.server.Logic.Pieces.Piece;
import com.ps.server.Logic.Position;

import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

public class OpeningBoardEvaluator extends StandardBoardEvaluator {
    Map<Position, Boolean> minorPiecesFirstMoveMap;

    public OpeningBoardEvaluator() {
        minorPiecesFirstMoveMap = new HashMap<>();
        minorPiecesFirstMoveMap.put(new Position(7, 1), true);
        minorPiecesFirstMoveMap.put(new Position(7, 2), true);
        minorPiecesFirstMoveMap.put(new Position(7, 5), true);
        minorPiecesFirstMoveMap.put(new Position(7, 6), true);
        minorPiecesFirstMoveMap.put(new Position(0, 1), true);
        minorPiecesFirstMoveMap.put(new Position(0, 2), true);
        minorPiecesFirstMoveMap.put(new Position(0, 5), true);
        minorPiecesFirstMoveMap.put(new Position(0, 6), true);
    }

    @Override
    protected int scorePlayer(final Board board, final Color color) {
        return scorePieces(board, color)
                + developPiece(board, color)
                + totalPiecesDeveloped(board, color)
                + pawnsInCenter(board, color)
                + castledKing(board, color)
                + rooksConnected(board, color);
        //TODO add score for developing pieces, total pieces developed, castling, moving central pawns, connecting rooks, subtract score for too early queen moves
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

    @SuppressWarnings("Duplicates")
    //TODO test, think about returned result
    private int developPiece(final Board board, final Color color) {
        Iterator<Map.Entry<Position, Boolean>> iterator = minorPiecesFirstMoveMap.entrySet().iterator();

        while(iterator.hasNext()) {
            Map.Entry<Position, Boolean> entry = iterator.next();

            if(color == Color.WHITE && entry.getKey().row != 7) {
                continue;
            }

            if(color == Color.BLACK && entry.getKey().row != 0) {
                continue;
            }

            if (board.getChessSquareState(entry.getKey()).state == ChessSquareState.State.EMPTY
                    && entry.getValue() == true) {
                minorPiecesFirstMoveMap.put(entry.getKey(), false);

                return 30;
            }
        }

        return 0;
    }

    private int totalPiecesDeveloped(final Board board, final Color color) {
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

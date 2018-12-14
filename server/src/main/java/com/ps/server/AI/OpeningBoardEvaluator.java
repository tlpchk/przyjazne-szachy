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
    private Map<Position, Boolean> minorPiecesFirstMoveMap;
    private Map<Color, Boolean> castledKing;

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

        castledKing = new HashMap<>();
        castledKing.put(Color.WHITE, false);
        castledKing.put(Color.BLACK, false);
    }

    @Override
    protected int scorePlayer(final Board board, final Color color) {
        return scorePieces(board, color)
                + developPiece(board, color)
                + totalPiecesDeveloped(board, color)
                + pawnsInCenter(board, color)
                + castledKing(board, color)
                + rooksConnected(board, color);
        //TODO add score for castling, moving central pawns, connecting rooks, subtract score for too early queen moves
    }

    //TODO implement
    private int rooksConnected(Board board, Color color) {
        return 0;
    }

    @SuppressWarnings("Duplicates")
    private int castledKing(Board board, Color color) {
        if(castledKing.get(color).booleanValue() == true) {
            return 90;
        }

        Position shortCastle;
        Position longCastle;

        if(color == Color.WHITE) {
            shortCastle = new Position(7, 6);
            longCastle = new Position(7, 2);
        }
        else {
            shortCastle = new Position(0, 6);
            longCastle = new Position(0, 2);
        }

        if(board.getChessSquareState(shortCastle).state == ChessSquareState.State.OCCUPIED
                && board.getChessSquareState(shortCastle).getPiece().type == Piece.PieceType.KING
                && board.getChessSquareState(new Position(shortCastle.row, shortCastle.col - 1)).state == ChessSquareState.State.OCCUPIED
                && board.getChessSquareState(new Position(shortCastle.row, shortCastle.col - 1)).getPiece().type == Piece.PieceType.ROOK) {
            castledKing.put(color, true);
            return 90;
        }

        if(board.getChessSquareState(longCastle).state == ChessSquareState.State.OCCUPIED
                && board.getChessSquareState(longCastle).getPiece().type == Piece.PieceType.KING
                && board.getChessSquareState(new Position(longCastle.row, longCastle.col - 1)).state == ChessSquareState.State.OCCUPIED
                && board.getChessSquareState(new Position(longCastle.row, longCastle.col + 1)).getPiece().type == Piece.PieceType.ROOK) {
            castledKing.put(color, true);
            return 90;
        }

        return 0;
    }

    @SuppressWarnings("Duplicates")
    private int pawnsInCenter(final Board board, final Color color) {
        int semiCenter = 0;
        int center = 0;

        int centralRow;
        int semiCentralRow;

        if(color == Color.WHITE) {
            centralRow = 4;
            semiCentralRow = 5;
        }
        else {
            centralRow = 3;
            semiCentralRow = 2;
        }

        for(int i = 2; i <= 4; i++) {
            Position centralPosition = new Position(centralRow, i);
            ChessSquareState centralSquareState = board.getChessSquareState(centralPosition);

            if(centralSquareState.state == ChessSquareState.State.OCCUPIED
                    && centralSquareState.getPiece().type == Piece.PieceType.PAWN
                    && centralSquareState.getPiece().color == color) {
                center++;
            }

            Position semiCentralPosition = new Position(semiCentralRow, i);
            ChessSquareState semiCentralSquareState = board.getChessSquareState(semiCentralPosition);

            if(semiCentralSquareState.state == ChessSquareState.State.OCCUPIED
                    && semiCentralSquareState.getPiece().type == Piece.PieceType.PAWN
                    && semiCentralSquareState.getPiece().color == color) {
                semiCenter++;
            }
        }

        return center * 30 + semiCenter * 20;
    }

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
        Iterator<Map.Entry<Position, Boolean>> iterator = minorPiecesFirstMoveMap.entrySet().iterator();
        int piecesDeveloped = 0;

        while(iterator.hasNext()) {
            Map.Entry<Position, Boolean> entry = iterator.next();

            if(color == Color.WHITE
                    && entry.getValue() == false
                    && entry.getKey().row == 7) {
                piecesDeveloped++;
            }

            if(color == Color.BLACK
                    && entry.getValue() == false
                    && entry.getKey().row == 0) {
                piecesDeveloped++;
            }
        }
        return 25 * piecesDeveloped;
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

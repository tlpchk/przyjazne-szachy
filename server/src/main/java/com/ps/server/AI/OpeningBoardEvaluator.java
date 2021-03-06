package com.ps.server.AI;

import com.ps.server.Logic.*;
import com.ps.server.Logic.Pieces.Piece;

import java.util.*;

/**
 * Evaluator dedicated opening phase during match
 */
public class OpeningBoardEvaluator extends StandardBoardEvaluator {
    /** Stores information about pieces development */
    private Map<Position, Boolean> minorPiecesFirstMoveMap;
    /** Stores information about castle*/
    private Map<Color, Boolean> castledKing;

    /**
     * Class constructor, setting MinorPiecesMoveMap and CastledKingMap
     */
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

    /**
     * Function counting points of given player position
     * @param board Board instance of interest
     * @param color pieces color
     * @return position score
     */
    @Override
    protected int scorePlayer(final Board board, final Color color) {
        return scorePieces(board, color)
                + developPiece(board, color)
                + totalPiecesDeveloped(board, color)
                + pawnsInCenter(board, color)
                + castledKing(board, color)
                + rooksConnected(board, color);
        //TODO add score for too early queen moves
    }

    /**
     * Checks if rooks are connected
     * @param board board situation
     * @param color player color
     * @return 30 if rooks are connected, 0 otherwise
     */
    private int rooksConnected(Board board, Color color) {
        List<Piece> pieces = board.getSet(color).getPieceSet();
        List<Piece> rooks = new ArrayList();

        for(Piece p : pieces)
        {
            if(p.type == Piece.PieceType.ROOK && p.getPosition() != null)
            {
                rooks.add(p);
            }
        }

        if(rooks.size() == 2)
        {
            if(rooks.get(0).getPosition().row == rooks.get(1).getPosition().row
                    || rooks.get(0).getPosition().col == rooks.get(1).getPosition().col)
            {
                return 30;
            }
        }

        return 0;
    }

    /**
     * Checks if king has castled
     * @param board Board instance
     * @param color player color
     * @return 90 if king has castled, 0 otherwise
     */
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

    /**
     * Adds score for pushing central pawns
     * @param board Board instance
     * @param color player color
     * @return score for pawns in center plus score for pawns in semi-center
     */
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

        return center * 50 + semiCenter * 25;
    }

    /**
     * Adds score for developing pieces
     * @param board Board instance
     * @param color player color
     * @return score if a piece has just been developed
     */
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

                return 10;
            }
        }

        return 0;
    }

    /**
     * Adds score for total pieces developed
     * @param board Board instance
     * @param color player color
     * @return number of total pieces developed multiplied by 25
     */
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

    /**
     * Table of heuristic pieces values
     * @param piece object with value assigned in this function
     * @return value of given piece
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
}

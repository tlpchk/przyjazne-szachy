package com.ps.server.logic;

import com.ps.server.logic.pieces.Piece;
import com.ps.server.logic.pieces.Queen;

import java.util.ArrayList;
import java.util.List;

import static com.ps.server.logic.Color.WHITE;
import static com.ps.server.logic.pieces.Piece.PieceType.KING;
import static com.ps.server.logic.pieces.Piece.PieceType.QUEEN;
import static com.ps.server.logic.pieces.Piece.PieceType.ROOK;

public class Board {
    public final static int COL_NUM = 8;
    public final static int ROW_NUM = 8;
    private Piece[][] board;
    private Set whiteSet;
    private Set blackSet;

    public Board(Set whiteSet, Set blackSet) {
        this.whiteSet = whiteSet;
        this.blackSet = blackSet;
        board = generateBoardFromSets(whiteSet, blackSet);
    }

    public ChessSquareState getChessSquareState(Position position) {
        if(position.row < 0 || position.column < 0 || position.column > 7 || position.row > 7)
            return null;
        return new ChessSquareState(board[position.row][position.column]);
    }

    public boolean ifEmpty(Position position) {
        ChessSquareState state = getChessSquareState(position);
        return state != null && state.state == ChessSquareState.State.EMPTY;
    }

    public boolean ifOccupiedByOpponent(Position position, Color color) {
        ChessSquareState state = getChessSquareState(position);
        return state != null && state.state == ChessSquareState.State.OCCUPIED && state.getPiece().color != color;
    }

    public List<Move> getLegalMoves(Color color) {
        Set set = (color == WHITE) ? whiteSet : blackSet;
        return set.legalMoves();
    }

    private Piece removePiece(Position loc) {
        Piece p = board[loc.row][loc.column];
        if(p != null) {
            p.move(null);
        }
        board[loc.row][loc.column] = null;
        return p;
    }

    private void addPiece(Piece piece, Position loc) {
        board[loc.row][loc.column] = piece;
        piece.move(loc);
    }

    private void giveEnPassantsRights(Position destination, Position loc, Color color) {
        for(int i = -1; i <= 2; i += 2) {
            int column = destination.column + i;
            if(column < 0 || column > 7) continue;
            Piece piece = board[destination.row][column];
            if(piece != null && piece.color != color) {
              piece.giveEnPassantRights(loc);
            }
        }
    }

    public void makeMove(Move move) {
        Position loc = move.loc;
        Position dest = move.dest;
        switch (move.type) {
            case PROMOTION:
                removePiece(dest);
                removePiece(loc);
                addPiece(new Queen(move.pieceColor, dest), dest);
                break;
            case EN_PASSANT:
                removePiece(new Position(loc.row, dest.column));
            case LONG_PAWN_MOVE:
                giveEnPassantsRights(dest, loc, move.pieceColor);
            case NORMAL:
                removePiece(dest);
                addPiece(removePiece(loc), dest);
                break;
            case LONG_CASTLE: {
                Piece rook = removePiece(dest);
                Piece king = removePiece(loc);
                addPiece(king, new Position(loc.row, loc.column - 2));
                addPiece(rook, new Position(dest.row, dest.column + 3));
                break;
            }
            case SHORT_CASTLE: {
                Piece rook = removePiece(dest);
                Piece king = removePiece(loc);
                addPiece(king, new Position(loc.row, loc.column + 2));
                addPiece(rook, new Position(dest.row, dest.column - 2));
                break;
            }
        }
    }

    public static List<Change> getListOfChanges(Move move) {
        List<Change> changes = new ArrayList<>();
        Position loc = move.loc;
        Position dest = move.dest;
        Color color = move.pieceColor;
        switch (move.type) {
            case PROMOTION:
                changes.add(new Change(loc, null, null));
                changes.add(new Change(dest, QUEEN, color));
                break;
            case EN_PASSANT:
                changes.add(new Change(new Position(loc.row, dest.column), null, null));
            case LONG_PAWN_MOVE:
            case NORMAL:
                changes.add(new Change(loc, null, null));
                changes.add(new Change(dest, move.pieceType, color));
                break;
            case LONG_CASTLE:
                changes.add(new Change(loc, null, null));
                changes.add(new Change(dest, null, null));
                changes.add(new Change(new Position(loc.row, loc.column - 2), KING, color));
                changes.add(new Change(new Position(dest.row, dest.column + 3), ROOK, color));
                break;
            case SHORT_CASTLE:
                changes.add(new Change(loc, null, null));
                changes.add(new Change(dest, null, null));
                changes.add(new Change(new Position(loc.row, loc.column + 2), KING, color));
                changes.add(new Change(new Position(dest.row, dest.column - 2), ROOK, color));
                break;
        }
        return changes;
    }

    public void updateGame(Color turn) {
        Set playingSet = (turn == WHITE) ? whiteSet : blackSet;
        playingSet.generateLegalMoves();
        if(!playingSet.checkIfCanMove()) {
            if(checkIfKingInCapture(turn)) {
                //TODO:: return checkmate
            }
            //TODO:: return stalemate
        }
    }

    public Move validatePlayersMove(Position loc, Position dest, Color color) {
        Piece piece = board[loc.row][loc.column];
        if(piece == null || piece.color != color) { return null; }
        return piece.getMoveTo(dest);
    }

    private boolean checkIfKingInCapture(Color color) {
        Set thisSet = (color == WHITE) ? whiteSet : blackSet;
        Set oppositeSet = (color == WHITE) ? blackSet : whiteSet;
        return oppositeSet.checkIfCanCaptureKingOn(thisSet.getKingsPosition());

    }

    public boolean validateIfAfterMoveKingNotInCapture(Move move) {
        Board boardCopy = copy();
        boardCopy.makeMove(move);
        return !boardCopy.checkIfKingInCapture(move.pieceColor);
    }


    private Piece[][] generateBoardFromSets(Set set1, Set set2) {
        Piece[][] board = new Piece[ROW_NUM][];
        for(int i = 0; i < ROW_NUM; i++) {
            board[i] = new Piece[COL_NUM];
        }
        set1.addSetToBoard(board);
        set1.addBoardToPieces(this);
        set2.addSetToBoard(board);
        set2.addBoardToPieces(this);
        return board;
    }

    public Board copy() {
        return new Board(whiteSet.copy(), blackSet.copy());
    }

    public Piece[][] getBoard() {
        return board;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(" ");
        for(int col = 0; col < COL_NUM; col++) {
            builder.append("|").append(col);
        }
        builder.append("|\n");
        for(int row = 0; row < ROW_NUM; row++) {
            builder.append(row);
            for(int col = 0; col < COL_NUM; col++) {
                builder.append("|");
                Piece piece = board[row][col];
                if(piece == null) {
                    builder.append(" ");
                } else {
                    builder.append(piece.toString());
                }
            }
            builder.append("|\n");
        }
        return builder.toString();
    }

    public Piece[][] getBoard() {
        return board;
    }
}

package com.ps.server.Logic;

import com.ps.server.Logic.Pieces.Piece;
import com.ps.server.Logic.Pieces.Queen;

import java.util.ArrayList;
import java.util.List;

import static com.ps.server.Logic.Color.WHITE;
import static com.ps.server.Logic.Pieces.Piece.PieceType.KING;
import static com.ps.server.Logic.Pieces.Piece.PieceType.QUEEN;
import static com.ps.server.Logic.Pieces.Piece.PieceType.ROOK;

public class Board {
    public final static int COL_NUM = 8;
    public final static int ROW_NUM = 8;
    Piece[][] board;
    Set whiteSet;
    Set blackSet;

    public Board(Set whiteSet, Set blackSet) {
        this.whiteSet = whiteSet;
        this.blackSet = blackSet;
        board = generateBoardFromSets(whiteSet, blackSet);
    }

    public Piece[][] getBoard() { return board;}

    Piece removePiece(Position loc) {
        Piece p = board[loc.row][loc.col];
        board[loc.row][loc.col] = null;
        return p;
    }

    void addPiece(Piece piece, Position loc) {
        board[loc.row][loc.col] = piece;
        piece.move(loc);
    }

    void giveEnPassantsRights(Position destination, Position loc, Color color) {
        for(int i = -1; i <= 2; i += 2) {
            int column = destination.col + i;
            if(column < 0 || column > 7) continue;
            Piece piece = board[destination.row][column];
            if(piece != null && piece.color != color) {
              piece.giveEnPassantRights(loc);
            }
        }
    }

    void makeMove(Move move) {
        Position loc = move.loc;
        Position dest = move.dest;
        switch (move.type) {
            case PROMOTION:
                removePiece(loc);
                addPiece(new Queen(move.pieceColor, dest), dest);
                break;
            case EN_PASSANT:
                removePiece(new Position(loc.row, dest.col));
            case LONG_PAWN_MOVE:
                giveEnPassantsRights(dest, loc, move.pieceColor);
            case NORMAL:
                addPiece(removePiece(loc), dest);
                break;
            case CASTLE:
                Piece rook = removePiece(dest);
                Piece king = removePiece(loc);
                addPiece(king, dest);
                addPiece(rook, loc);
                break;
        }
    }

    static List<Change> getListOfChanges(Move move) {
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
                changes.add(new Change(new Position(loc.row, dest.col), null, null));
            case LONG_PAWN_MOVE:
            case NORMAL:
                changes.add(new Change(loc, null, null));
                changes.add(new Change(dest, move.pieceType, color));
                break;
            case CASTLE:
                changes.add(new Change(dest, KING, color));
                changes.add(new Change(loc, ROOK, color));
                break;
        }
        return changes;
    }

    void updateGame(Color turn) {
        Set playingSet = (turn == WHITE) ? whiteSet : blackSet;
        playingSet.generateLegalMoves();
        if(!playingSet.checkIfCanMove()) {
            if(checkIfKingInCapture(turn)) {
                //TODO:: return checkmate
            }
            //TODO:: return stalemate
        }
    }

    Move validatePlayersMove(Position loc, Position dest, Color color) {
        Piece piece = board[loc.row][loc.col];
        if(piece == null || piece.color != color) { return null; }
        return piece.getMoveTo(dest);
    }

    public boolean checkIfKingInCapture(Color color) {
        Set thisSet = (color == WHITE) ? whiteSet : blackSet;
        Set oppositeSet = (color == WHITE) ? blackSet : whiteSet;
        return oppositeSet.checkIfCanCaptureKingOn(thisSet.getKingsPosition());

    }

    public boolean validateIfAfterMoveKingNotInCapture(Move move) {
        Board boardCopy = copy();
        boardCopy.makeMove(move);
        return !boardCopy.checkIfKingInCapture(move.pieceColor);
    }


    Piece[][] generateBoardFromSets(Set set1, Set set2) {
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

    Board copy() {
        return new Board(whiteSet.copy(), blackSet.copy());
    }

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

}

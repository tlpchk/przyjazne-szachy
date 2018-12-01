package com.ps.server.logic.pieces;

import com.fasterxml.jackson.annotation.JsonValue;
import com.ps.server.logic.Board;
import com.ps.server.logic.Color;
import com.ps.server.logic.Move;
import com.ps.server.logic.Position;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Piece {
    List<Move> legalMoves;
    final public Color color;
    final PieceType type;
    Board board;
    Position position;

    Piece(Color color, PieceType type, Position position) {
        this.color = color;
        this.type = type;
        this.position = position;
    }

    public void addBoard(Board board) {
        this.board = board;
    }

    abstract public List<Move> semiLegalMoves();

    public void legalMoves() {
        legalMoves = semiLegalMoves()
                .stream()
                .filter(board::validateIfAfterMoveKingNotInCapture)
                .collect(Collectors.toList());
    }

    abstract public boolean checkIfCanCaptureKingOn(Position kingsPosition);

    public enum PieceType {
        KING("king"), QUEEN("queen"), PAWN("pawn"), ROOK("rook"), KNIGHT("knight"), BISHOP("bishop");

        private String pieceTypeString;

        PieceType(String pieceTypeString) {
            this.pieceTypeString = pieceTypeString;
        }

        @JsonValue
        public String getPieceTypeString() {
            return pieceTypeString;
        }
    }

    public abstract Piece copy();

    public Position getPosition() {
        return position;
    }

    public void move(Position position) {
        this.position = position;
    }

    public Move getMoveTo(Position dest) {
        return legalMoves.stream().filter(move -> move.dest.equalsToPos(dest)).findFirst().orElse(null);
    }

    public boolean canMove() {
        return legalMoves.size() != 0;
    }

    boolean hasCastleRights() {
        return false;
    }

    public void giveEnPassantRights(Position position) {}

    public List<Move> getListOfMoves() {
        return legalMoves;
    }

    public PieceType getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public List<Move> getLegalMoves() {
        return legalMoves;
    }
}

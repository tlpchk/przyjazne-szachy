package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.Board;
import com.ps.server.Logic.Color;
import com.ps.server.Logic.Move;
import com.ps.server.Logic.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Piece {
    List<Move> legalMoves = new ArrayList<>();
    final public Color color;
    public final PieceType type;
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
        KING, QUEEN, PAWN, ROOK, KNIGHT, BISHOP
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
}

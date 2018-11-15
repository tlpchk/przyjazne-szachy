package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.Board;
import com.ps.server.Logic.Color;
import com.ps.server.Logic.Move;
import com.ps.server.Logic.Position;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Piece {
    List<Move> legalMoves;
    final public Color color;
    final public PieceType type;
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

    int moveOrCaptureOrNoMove(Position position) { //0 move, 1 capture, 2 no move
        if(position.col < 0 || position.row < 0 || position.row > 7 || position.col > 7) {
            return -1;
        }
        Piece captured = board.getBoard()[position.row][position.col];
        if(captured == null ) return 0;
        if(captured.color != color) return 1;
        return -1;
    }

    boolean hasCastleRights() {
        return false;
    }

    public void giveEnPassantRights(Position position) {}

    public List<Move> getListOfMoves() {
        return legalMoves;
    }
}

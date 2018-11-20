package com.ps.server.Logic;

import com.ps.server.Logic.Pieces.Piece;

import java.util.List;
import java.util.stream.Collectors;

public class Set {
    private final Color color;
    private final List<Piece> set;// king is the head

    public Set(Color color, List<Piece> set) {
        this.color = color;
        this.set = set;
    }

    List<Move> generateSemiLegalMoves() {
        return set.stream().flatMap(piece -> piece.semiLegalMoves().stream()).collect(Collectors.toList());
    }

    void generateLegalMoves() {
        set.forEach(Piece::legalMoves);
    }

    boolean checkIfCanCaptureKingOn(Position kingsPosition) {
        return set.stream()
                .filter(p -> p.checkIfCanCaptureKingOn(kingsPosition))
                .findFirst()
                .orElse(null) != null;
    }

    void addSetToBoard(Piece[][] board) {
        if(set == null) return;
        set.forEach(piece -> {
            Position position = piece.getPosition();
            board[position.row][position.col] = piece;
        });
    }

    void addBoardToPieces(Board board) {
        if(set == null) return;
        set.forEach(piece -> piece.addBoard(board));
    }

    Position getKingsPosition() {
        return set.get(0).getPosition();
    }

    Set copy() {
        List<Piece> newSet = set.stream().map(Piece::copy).collect(Collectors.toList());
        return new Set(color, newSet);
    }

    boolean checkIfCanMove() {
        for(int i = set.size()-1; i > 0; i--) {
            if(set.get(i).canMove()) {
                return true;
            }
        }
        return false;
    }

}
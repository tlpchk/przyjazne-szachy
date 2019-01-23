package com.ps.server.Logic;

import com.ps.server.Logic.Pieces.Piece;

import java.util.List;
import java.util.stream.Collectors;

public class Set {
    final Color color;
    private List<Piece> set;

    /**
     * Class constructor
     * @param color set color (WHITE, BLACK)
     * @param set list of pieces
     *            needs to begin with a king for rules to work properly
     */
    public Set(Color color, List<Piece> set) {
        assert set == null || (set.get(0).type == Piece.PieceType.KING);
        this.color = color;
        this.set = set;
    }

    /**
     * Getter for list of pieces held by this class
     * @return List of pieces
     */
    public List<Piece> getPieceSet()
    {
        return this.set;
    }

    List<Move> semiLegalMoves() {
        return set.stream().flatMap(piece -> piece.semiLegalMoves().stream()).collect(Collectors.toList());
    }

    List<Move> legalMoves() {
        set = set.stream().filter(p -> p.getPosition() != null).collect(Collectors.toList());
        return set.stream().flatMap(piece -> piece.getListOfMoves().stream()).collect(Collectors.toList());
    }

    void generateLegalMoves() {
        set = set.stream().filter(p -> p.getPosition() != null).collect(Collectors.toList());
        set.forEach(Piece::update);
    }

    boolean checkIfCanCaptureKingOn(Position kingsPosition) {
        return set.stream()
                .filter(p -> p.getPosition() != null && p.checkIfCanCaptureKingOn(kingsPosition))
                .findFirst()
                .orElse(null) != null;
    }

    void addSetToBoard(Piece[][] board) {
        if(set == null) return;
        set.forEach(piece -> {
            Position position = piece.getPosition();
            if(position != null)
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
        for(int i = set.size()-1; i >= 0; i--) {
            if(set.get(i).canMove()) {
                return true;
            }
        }
        return false;
    }

    void addPieceToSet(Piece piece,Board board){
        set.add(piece);
        piece.addBoard(board);
    }


    public List<Piece> getSet() {
        return set;
    }
}

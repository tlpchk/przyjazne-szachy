package com.ps.server.logic.pieces;

import com.ps.server.logic.Color;
import com.ps.server.logic.Move;
import com.ps.server.logic.Position;

import java.util.ArrayList;
import java.util.List;

import static com.ps.server.logic.Color.WHITE;
import static com.ps.server.logic.Move.MoveType.NORMAL;
import static com.ps.server.logic.pieces.Piece.PieceType.KNIGHT;

public class Knight extends Piece {

    public Knight(Color color, Position position) {
        super(color, KNIGHT, position);
    }

    @Override
    public String toString() {
        return color == WHITE ? "♞" : "♘";
    }

    private Move createMove(int row, int col) {
        if(board.ifEmpty(position) || board.ifOccupiedByOpponent(position, color)) {
            return new Move(this.type, color, position, new Position(row, col), NORMAL);
        }
        return null;
    }

    @Override
    public List<Move> semiLegalMoves() {
        List<Move> legalMoves = new ArrayList<>();
        int[] distOne = {-1, 1};
        int[] distTwo = {-2, 2};

        for(int i = 0; i < 2; i ++) {
            for(int j = 0; j < 2; j ++) {
               Move move = createMove(position.row + distOne[i], position.column + distTwo[j]);
               if(move != null) { legalMoves.add(move);}
               move = createMove(position.row + distTwo[i], position.column + distOne[j]);
               if(move != null) { legalMoves.add(move);}
            }
        }
        return legalMoves;
    }


    @Override
    public boolean checkIfCanCaptureKingOn(Position kingsPosition) {
        int distX = Math.abs(position.column - kingsPosition.column);
        int distY = Math.abs(position.row - kingsPosition.row);
        return (distY == 1 && distX == 2) || (distY == 2 && distX == 1);
    }

    @Override
    public Piece copy() {
        return new Knight(color, position);
    }
}

package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.ChessSquareState;
import com.ps.server.Logic.Color;
import com.ps.server.Logic.Move;
import com.ps.server.Logic.Position;

import java.util.ArrayList;
import java.util.List;

import static com.ps.server.Logic.Color.WHITE;
import static com.ps.server.Logic.Move.MoveType.NORMAL;
import static com.ps.server.Logic.Pieces.Piece.PieceType.KNIGHT;

public class Knight extends Piece {

    public Knight(Color color, Position position) {
        super(color, KNIGHT, position);
    }

    @Override
    public String toString() {
        return color == WHITE ? "♞" : "♘";
    }

    private Move createMove(int row, int col) {
        Position newPos = new Position(row, col);
        if(board.ifEmpty(newPos) || board.ifOccupiedByOpponent(newPos, color)) {
            return new Move(this.type, color, position, newPos, NORMAL);
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
               Move move = createMove(position.row + distOne[i], position.col + distTwo[j]);
               if(move != null) { legalMoves.add(move);}
               move = createMove(position.row + distTwo[i], position.col + distOne[j]);
               if(move != null) { legalMoves.add(move);}
            }
        }
        return legalMoves;
    }


    @Override
    public boolean checkIfCanCaptureKingOn(Position kingsPosition) {
        int distX = Math.abs(position.col - kingsPosition.col);
        int distY = Math.abs(position.row - kingsPosition.row);
        return (distY == 1 && distX == 2) || (distY == 2 && distX == 1);
    }

    @Override
    public Piece copy() {
        return new Knight(color, position);
    }
}

package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.ChessSquareState;
import com.ps.server.Logic.Color;
import com.ps.server.Logic.Move;
import com.ps.server.Logic.Position;

import java.util.ArrayList;
import java.util.List;

import static com.ps.server.Logic.ChessSquareState.State.OCCUPIED;
import static com.ps.server.Logic.Move.MoveType.NORMAL;

abstract class StraightMovingPieces extends Piece {

    StraightMovingPieces(Color color, PieceType type, Position position) {
        super(color, type, position);
    }

    List<Move> generateMovesInDirection(int diffOnRow, int diffOnColumn) {
        List<Move> legalMovesUp = new ArrayList<>();
        int column = position.col + diffOnColumn;
        int row = position.row + diffOnRow;

        while(board.getChessSquareState(new Position(row, column)) != null) {
            if(board.ifEmpty(new Position(row, column))) {
                Move move = new Move(this.type, color, position, new Position(row, column), NORMAL);
                legalMovesUp.add(move);
            } else if(board.ifOccupiedByOpponent(new Position(row, column), color)) {
                Move move = new Move(this.type, color, position, new Position(row, column), NORMAL);
                legalMovesUp.add(move);
                break;
            } else {
                break;
            }
            column += diffOnColumn;
            row += diffOnRow;
        }
        return legalMovesUp;
    }

    private int direction(int oldV, int newV) {
        return Integer.compare(newV, oldV);
    }

    boolean checkIfSomethingBetween(Position destination) {
        int diffOnRow = direction(position.row, destination.row);
        int diffOnColumn = direction(position.col, destination.col);
        int column = position.col + diffOnColumn;
        int row = position.row + diffOnRow;
        int distOnColumn = Math.abs(column - position.col);
        int distOnRow = Math.abs(row - position.row);
        int maxDistOnColumn = Math.abs(destination.col - position.col);
        int maxDistOnRow = Math.abs(destination.row - position.row);

        while(distOnRow < maxDistOnRow || distOnColumn < maxDistOnColumn) {
            ChessSquareState state = board.getChessSquareState(new Position(row, column));
//            if(state == null) return false;
            if(state.state == OCCUPIED) return true;
            column += diffOnColumn;
            row += diffOnRow;
            distOnColumn = Math.abs(column - position.col);
            distOnRow = Math.abs(row - position.row);
        }
        return false;
    }
}

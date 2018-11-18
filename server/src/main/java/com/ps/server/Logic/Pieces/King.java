package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.ps.server.Logic.Color.WHITE;
import static com.ps.server.Logic.Move.MoveType.*;
import static com.ps.server.Logic.Pieces.Piece.PieceType.KING;

public class King extends StraightMovingPieces {
    private boolean castleRights;
    private static Position[] whiteRooks = SetFactory.WhiteSetFactory.rookPositions;
    private static Position[] blackRooks = SetFactory.BlackSetFactory.rookPositions;

    public King(Color color, Position position) {
        super(color, KING, position);
        castleRights = true;
    }

    private List<Move> generateCastleMoves() {
        if(!castleRights) return null;
        List<Move> legalMoves = new ArrayList<>();
        Position[] rooks = (color == WHITE) ? whiteRooks : blackRooks;
        for(int i = 0; i < 2; i++) {
            Piece rook = board.getChessSquareState(rooks[i]).getPiece();
            if(rook != null && rook.hasCastleRights() && rook.color == color && !checkIfSomethingBetween(rooks[i])) {
                legalMoves.add(new Move(this.type, this.color, this.position, rooks[i], CASTLE));
            }
        }
        return legalMoves;
    }

    @Override
    public List<Move> semiLegalMoves() {
        List<Move> legalMoves = new ArrayList<>();
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                Position destination = new Position(position.row + i, position.col + j);
                if(board.ifEmpty(destination) || board.ifOccupiedByOpponent(destination, color)) {
                    legalMoves.add(new Move(this.type, color, this.position, destination, NORMAL));
                }
            }
        }
        List<Move> castleMoves = generateCastleMoves();
        if(castleMoves != null)
            legalMoves.addAll(castleMoves);
        return legalMoves;
    }

    @Override
    public boolean checkIfCanCaptureKingOn(Position kingsPosition) {
        return false;
    }

    @Override
    public Piece copy() {
        return new King(color, position);
    }

    @Override
    public void move(Position destination) {
        castleRights = false;
        super.move(destination);
    }

    @Override
    public String toString() {
        return color == WHITE ? "♚" : "♔";
    }
}

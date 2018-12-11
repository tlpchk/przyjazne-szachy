package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.*;
import org.junit.Test;

import java.util.Arrays;

import static com.ps.server.Logic.Color.BLACK;
import static com.ps.server.Logic.Color.WHITE;
import static com.ps.server.Logic.Pieces.Piece.PieceType.KNIGHT;
import static org.junit.Assert.assertEquals;

public class KingTest {
    King king = new King(WHITE, SetFactory.WhiteSetFactory.kingsPosition);

    @Test
    public void test() {
        Position blackKingPos = SetFactory.WhiteSetFactory.kingsPosition;
        Set black = new Set(BLACK, Arrays.asList(
                new King(BLACK, blackKingPos)
        ));
        Set white = new Set(WHITE, Arrays.asList(
                new King(WHITE, SetFactory.WhiteSetFactory.kingsPosition),
                new Pawn(WHITE, new Position(1, 5))
        ));
        Board board = new Board(white, black);
        board.updateGame(WHITE);
        Move move = board.validatePlayersMove(blackKingPos, new Position(blackKingPos.row,blackKingPos.col+1), WHITE);
        move.setPromoteTo(KNIGHT);
        board.makeMove(move);
        assertEquals(KNIGHT, board.getChessSquareState(new Position(0,5)).getPiece().type);
    }

}

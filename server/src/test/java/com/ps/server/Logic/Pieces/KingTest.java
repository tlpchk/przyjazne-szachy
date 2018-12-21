package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.*;
import org.junit.Test;

import java.util.Arrays;

import static com.ps.server.Logic.Color.BLACK;
import static com.ps.server.Logic.Color.WHITE;

public class KingTest {
    @Test
    public void test() {
        Position blackKingPos = SetFactory.BlackSetFactory.kingsPosition;
        King king = new King(BLACK, blackKingPos);
        Set black = new Set(BLACK, Arrays.asList(
                king
        ));
        Set white = new Set(WHITE, Arrays.asList(
                new King(WHITE, SetFactory.WhiteSetFactory.kingsPosition),
                new Pawn(WHITE, new Position(1, 5))
        ));
        Board board = new Board(white, black);
        board.updateGame(BLACK);
        Move move = board.validatePlayersMove(blackKingPos, new Position(blackKingPos.row,blackKingPos.col+1), BLACK);
        board.makeMove(move);
        assert(!king.hasCasteRights());
    }
}

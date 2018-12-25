package com.ps.server.AI;

import com.ps.server.Logic.Board;
import com.ps.server.Logic.Move;
import com.ps.server.Logic.Pieces.*;
import com.ps.server.Logic.Position;
import com.ps.server.Logic.Set;
import org.junit.Test;

import java.util.Arrays;

import static com.ps.server.Logic.Color.BLACK;
import static com.ps.server.Logic.Color.WHITE;
import static org.junit.Assert.assertSame;

public class DiscoveryTest {
    @Test
    public void test() {
        Set blackSet = new Set(BLACK, Arrays.asList(
                new King(BLACK, new Position(0, 6)),
                new Queen(BLACK, new Position(2, 4))
        ));

        Set whiteSet =  new Set(WHITE, Arrays.asList(
                new King(WHITE, new Position(7, 5)),
                new Rook(WHITE, new Position(7, 4)),
                new Bishop(WHITE, new Position(4, 4))
        ));

        Board board = new Board(whiteSet, blackSet);
        System.out.println(board.toString());

        board.updateGame(WHITE);
        MoveStrategy moveStrategy = new AlphaBeta();
        Move move = moveStrategy.execute(board, 4, WHITE);
        assertSame(move.dest.col, 7);
        assertSame(move.dest.row, 1);

        board.makeMove(move);

        System.out.println(board.toString());
    }
}

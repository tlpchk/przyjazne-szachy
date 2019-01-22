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

public class DefenderDeflect {
    @Test
    public void test() {
        Set blackSet = new Set(BLACK, Arrays.asList(
                new King(BLACK, new Position(1, 7)),
                new Queen(BLACK, new Position(3, 6)),
                new Knight(BLACK, new Position(2, 6)),
                new Pawn(BLACK, new Position(1, 6)),
                new Pawn(BLACK, new Position(2, 7)),
                new Pawn(BLACK, new Position(1, 0)),
                new Pawn(BLACK, new Position(2, 1))
        ));

        Set whiteSet = new Set(WHITE, Arrays.asList(
                new King(WHITE, new Position(7, 1)),
                new Rook(WHITE, new Position(2, 4)),
                new Queen(WHITE, new Position(4, 4)),
                new Pawn(WHITE, new Position(4, 6)),
                new Pawn(WHITE, new Position(5, 7)),
                new Pawn(WHITE, new Position(5, 0)),
                new Pawn(WHITE, new Position(6, 1)),
                new Pawn(WHITE, new Position(6, 2))
        ));

        Board board = new Board(whiteSet, blackSet);
        System.out.println(board.toString());

        board.updateGame(WHITE);
        MoveStrategy moveStrategy = new AlphaBeta();
        Move move = moveStrategy.execute(board, 4, WHITE);
        assertSame(move.dest.col, 7);
        assertSame(move.dest.row, 4);

        board.makeMove(move);

        System.out.println(board.toString());
    }
}

package com.ps.server.AI;

import com.ps.server.Logic.*;
import com.ps.server.Logic.Pieces.King;
import com.ps.server.Logic.Pieces.Pawn;
import com.ps.server.Logic.Pieces.Piece;
import com.ps.server.Logic.Pieces.Rook;
import org.junit.Test;

import java.util.Arrays;

import static com.ps.server.Logic.Color.BLACK;
import static com.ps.server.Logic.Color.WHITE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class MinMaxTest {
    @Test
    public void correctMoveChoiceTest() {
        Rook whiteRook = new Rook(WHITE, new Position(1, 4));
        Pawn whitePawn = new Pawn(WHITE, new Position(6, 5));

        Set whiteSet = new Set(WHITE, Arrays.asList(
                new King(WHITE, new Position(0, 4)),
                whiteRook,
                whitePawn
        ));

        Set blackSet =  new Set(BLACK, Arrays.asList(
                new King(BLACK, new Position(7, 4)),
                new Rook(BLACK, new Position(5, 4))
        ));

        Board board = new Board(whiteSet, blackSet);
        board.updateGame(Color.WHITE);
        System.out.print(board.toString());

        MoveStrategy moveStrategy = new MinMax();
        StandardBoardEvaluator standardBoardEvaluator = new StandardBoardEvaluator();

        assertEquals(1, standardBoardEvaluator.evaluate(board));
        System.out.println(standardBoardEvaluator.evaluate(board));

        Move move = moveStrategy.execute(board, 4, Color.WHITE);
        assertSame(Piece.PieceType.PAWN ,move.pieceType);
        assertSame(Move.MoveType.NORMAL,move.type);
        assertSame(new Position(6, 5).col, move.loc.col);
        assertSame(new Position(6, 5).row, move.loc.row);
        assertSame(new Position(5, 4).col, move.dest.col);
        assertSame(new Position(5, 4).row, move.dest.row);
        board.makeMove(move);

        System.out.print(board.toString());

        assertEquals(6, standardBoardEvaluator.evaluate(board));
        System.out.println(standardBoardEvaluator.evaluate(board));
    }
}

package com.ps.server.AI;

import com.ps.server.Logic.Board;

import com.ps.server.Logic.Pieces.Bishop;
import com.ps.server.Logic.Pieces.King;
import com.ps.server.Logic.Pieces.Rook;
import com.ps.server.Logic.Position;
import com.ps.server.Logic.Set;
import org.junit.Test;

import java.util.Arrays;

import static com.ps.server.Logic.Color.BLACK;
import static com.ps.server.Logic.Color.WHITE;
import static org.junit.Assert.*;

public class StandardBoardEvaluatorTest {
    @Test
    public void pieceValueTest() {
        Rook whiteRook = new Rook(WHITE, new Position(1, 4));
        Set whiteSet = new Set(WHITE, Arrays.asList(
                new King(WHITE, new Position(0, 4)),
                whiteRook
        ));

        Set blackSet =  new Set(BLACK, Arrays.asList(
                new King(BLACK, new Position(7, 4)),
                new Rook(BLACK, new Position(6, 4))
        ));
        Board board = new Board(whiteSet, blackSet);
        System.out.print(board.toString());

        StandardBoardEvaluator standardBoardEvaluator = new StandardBoardEvaluator();

        assertEquals(0, standardBoardEvaluator.evaluate(board));
    }

    @Test
    public void pieceValueTestWhiteIsInLead() {
        Rook whiteRook = new Rook(WHITE, new Position(1, 4));
        Bishop whiteBishop = new Bishop(WHITE, new Position(0, 0));
        Set whiteSet = new Set(WHITE, Arrays.asList(
                new King(WHITE, new Position(0, 4)),
                whiteRook,
                whiteBishop
        ));

        Set blackSet =  new Set(BLACK, Arrays.asList(
                new King(BLACK, new Position(7, 4)),
                new Rook(BLACK, new Position(6, 4))
        ));
        Board board = new Board(whiteSet, blackSet);
        System.out.print(board.toString());

        StandardBoardEvaluator standardBoardEvaluator = new StandardBoardEvaluator();

        assertEquals(3, standardBoardEvaluator.evaluate(board));
    }
}

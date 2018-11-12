package com.ps.server.AI;

import com.ps.server.Logic.Board;

import org.junit.Test;
import static org.junit.Assert.*;

public class StandardBoardEvaluatorTest {
    @Test
    public void pieceValueTest() {
        Board board = new Board();
        StandardBoardEvaluator standardBoardEvaluator = new StandardBoardEvaluator();

        assertEquals(0, standardBoardEvaluator.evaluate(board));
    }
}

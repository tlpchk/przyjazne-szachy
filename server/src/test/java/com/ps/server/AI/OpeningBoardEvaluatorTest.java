package com.ps.server.AI;

import com.ps.server.Logic.*;
import org.junit.Test;

import static com.ps.server.Logic.Color.BLACK;
import static com.ps.server.Logic.Color.WHITE;

public class OpeningBoardEvaluatorTest {
    @Test
    public void test() {
        Set whiteSet = new SetFactory.WhiteSetFactory().createSet();
        Set blackSet = new SetFactory.BlackSetFactory().createSet();
        Board board = new Board(whiteSet, blackSet);

        board.updateGame(WHITE);
        Move move1 = board.validatePlayersMove(new Position(7, 1), new Position(5, 2), WHITE);
        board.makeMove(move1);

        System.out.print(board.toString());

        OpeningBoardEvaluator op = new OpeningBoardEvaluator();
        System.out.println(op.evaluate(board));

        board.updateGame(BLACK);
        Move move2 = board.validatePlayersMove(new Position(0, 1), new Position(2, 2), BLACK);
        board.makeMove(move2);

        System.out.println(board.toString());
        System.out.println(op.evaluate(board));
    }
}

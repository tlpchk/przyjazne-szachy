package com.ps.server.AI;

import com.ps.server.Logic.*;
import org.junit.Test;

import static com.ps.server.Logic.Color.BLACK;
import static com.ps.server.Logic.Color.WHITE;
import static org.junit.Assert.assertEquals;

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

        OpeningBoardEvaluator openingBoardEvaluator = new OpeningBoardEvaluator();
        int value = openingBoardEvaluator.evaluate(board);
        //assertEquals(55, value);
        System.out.println(value);

        board.updateGame(BLACK);
        Move move2 = board.validatePlayersMove(new Position(0, 1), new Position(2, 2), BLACK);
        board.makeMove(move2);
        System.out.println(board.toString());

        value = openingBoardEvaluator.evaluate(board);
        //assertEquals(-30, value);
        System.out.println(value);

        board.updateGame(WHITE);
        Move move3 = board.validatePlayersMove(new Position(6, 3), new Position(4, 3), WHITE);
        board.makeMove(move3);
        System.out.println(board.toString());

        value = openingBoardEvaluator.evaluate(board);
        //assertEquals(30, value);
        System.out.println(value);

        board.updateGame(BLACK);
        Move move4 = board.validatePlayersMove(new Position(1, 3), new Position(3, 3), BLACK);
        board.makeMove(move4);
        System.out.println(board.toString());

        value = openingBoardEvaluator.evaluate(board);
        assertEquals(0, value);
        System.out.println(value);

        board.updateGame(WHITE);
        Move move5 = board.validatePlayersMove(new Position(7, 2), new Position(4, 5), WHITE);
        board.makeMove(move5);
        System.out.println(board.toString());

        value = openingBoardEvaluator.evaluate(board);
        //assertEquals(55, value);
        System.out.println(value);

        board.updateGame(BLACK);
        Move move6 = board.validatePlayersMove(new Position(0, 2), new Position(3, 5), BLACK);
        board.makeMove(move6);
        System.out.println(board.toString());

        value = openingBoardEvaluator.evaluate(board);
        //assertEquals(-30, value);
        System.out.println(value);

        board.updateGame(WHITE);
        Move move7 = board.validatePlayersMove(new Position(7, 3), new Position(6, 3), WHITE);
        board.makeMove(move7);
        System.out.println(board.toString());

        value = openingBoardEvaluator.evaluate(board);
        //assertEquals(0, value);
        System.out.println(value);

        board.updateGame(BLACK);
        Move move8 = board.validatePlayersMove(new Position(0, 3), new Position(1, 3), BLACK);
        board.makeMove(move8);
        System.out.println(board.toString());

        value = openingBoardEvaluator.evaluate(board);
        //assertEquals(0, value);
        System.out.println(value);

        board.updateGame(WHITE);
        Move move9 = board.validatePlayersMove(new Position(7, 4), new Position(7, 0), WHITE);
        board.makeMove(move9);
        System.out.println(board.toString());

        value = openingBoardEvaluator.evaluate(board);
        //assertEquals(90, value);
        System.out.println(value);

        board.updateGame(BLACK);
        Move move10 = board.validatePlayersMove(new Position(1, 7), new Position(2, 7), BLACK);
        board.makeMove(move10);
        System.out.println(board.toString());

        value = openingBoardEvaluator.evaluate(board);
        //assertEquals(90, value);
        System.out.println(value);
    }
}

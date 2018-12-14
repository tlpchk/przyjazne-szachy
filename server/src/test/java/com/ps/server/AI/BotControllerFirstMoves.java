package com.ps.server.AI;

import com.ps.server.Logic.*;
import org.junit.Test;

import static com.ps.server.Logic.Color.WHITE;

public class BotControllerFirstMoves {
    @Test
    public void test() {
        Set whiteSet = new SetFactory.WhiteSetFactory().createSet();
        Set blackSet = new SetFactory.BlackSetFactory().createSet();
        Board board = new Board(whiteSet, blackSet);

        board.updateGame(WHITE);
        BotController botController = new BotController();
        Move move = botController.execute(board, WHITE);
        board.makeMove(move);
        System.out.println(board.toString());

        OpeningBoardEvaluator op = new OpeningBoardEvaluator();
        System.out.println(op.evaluate(board));

        board.updateGame(WHITE);
        Move move2 = botController.execute(board, WHITE);
        board.makeMove(move2);
        System.out.println(board.toString());
        System.out.println(op.evaluate(board));

        board.updateGame(WHITE);
        Move move3 = botController.execute(board, WHITE);
        board.makeMove(move3);
        System.out.println(board.toString());
        System.out.println(op.evaluate(board));

        board.updateGame(WHITE);
        Move move4 = botController.execute(board, WHITE);
        board.makeMove(move4);
        System.out.println(board.toString());
        System.out.println(op.evaluate(board));

        board.updateGame(WHITE);
        Move move5 = botController.execute(board, WHITE);
        board.makeMove(move5);
        System.out.println(board.toString());
    }
}

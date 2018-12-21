package com.ps.server.Logic;

import org.junit.Test;

import static com.ps.server.Logic.Color.BLACK;
import static com.ps.server.Logic.Color.WHITE;

public class EnPassantTest {
    @Test
    public void test() {
        Set whiteSet = new SetFactory.WhiteSetFactory().createSet();
        Set blackSet = new SetFactory.BlackSetFactory().createSet();
        Board board = new Board(whiteSet, blackSet);

        board.updateGame(WHITE);
        Move move1 = board.validatePlayersMove(new Position(6, 0), new Position(4, 0), WHITE);
        board.makeMove(move1);
        System.out.print(board.toString());

        board.updateGame(BLACK);
        Move move2 = board.validatePlayersMove(new Position(1, 7), new Position(2, 7), BLACK);
        board.makeMove(move2);
        System.out.println(board.toString());

        board.updateGame(WHITE);
        Move move3 = board.validatePlayersMove(new Position(4, 0), new Position(3, 0), WHITE);
        board.makeMove(move3);
        System.out.println(board.toString());

        board.updateGame(BLACK);
        Move move4 = board.validatePlayersMove(new Position(1, 1), new Position(3, 1), BLACK);
        board.makeMove(move4);
        System.out.println(board.toString());

        board.updateGame(WHITE);
        for(Move move : board.getLegalMoves(WHITE)) {
            System.out.println(move.loc + " " + move.dest);
        }
    }
}

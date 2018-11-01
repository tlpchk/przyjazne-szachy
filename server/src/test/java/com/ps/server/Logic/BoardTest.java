package com.ps.server.Logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
    Board board = new Board();

    @Test
    public void generateTest() {
        System.out.print(board.toString() + "\n" + "\n");
        assert(board.getBoard()[6][7].piece.color == Color.WHITE);
    }

    @Test
    public void movePawnTest() {
        assert(board.makeMove(new Move(new Position(6, 7), new Position(5, 7), Color.WHITE)));
        System.out.print(board.toString());
    }

    @Test
    public void moveKnightTest() {
        assert(board.makeMove(new Move(new Position(7, 6), new Position(5, 5), Color.WHITE)));
        System.out.print(board.toString());
    }

    @Test
    public void wrongMoveKnightTest() {
        assert(!board.makeMove(new Move(new Position(7, 6), new Position(4, 5), Color.WHITE)));
        System.out.print(board.toString());
    }
}
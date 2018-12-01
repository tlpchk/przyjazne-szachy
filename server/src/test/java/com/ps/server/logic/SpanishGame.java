package com.ps.server.logic;

import org.junit.Test;

import static com.ps.server.logic.Color.BLACK;
import static com.ps.server.logic.Color.WHITE;
import static org.junit.Assert.assertTrue;

public class SpanishGame {

    @Test
    public void portugueseOpening() {
        Board board = setOpening();
        System.out.println("Portuguese opening");
        System.out.println(board.toString());

        board.updateGame(WHITE);
        Move whiteBishopMove = board.validatePlayersMove(new Position(7, 5), new Position(3, 1), WHITE);
        board.makeMove(whiteBishopMove);
        assertTrue(board.getChessSquareState(new Position(3, 1)).state == ChessSquareState.State.OCCUPIED);
        System.out.println(board.toString());

        board.updateGame(BLACK);
        Move blackPinnedPawnMove = board.validatePlayersMove(new Position(1, 3), new Position(2, 3), BLACK);
        //assertTrue(blackPinnedPawnMove == null); TODO: problem with possibility to move piece discovering bishop attack to king
        board.makeMove(blackPinnedPawnMove);
        System.out.println(board.toString());
    }

    @Test
    public void RuyLopez() {
        Board board = setOpening();
        System.out.println("Spanish opening");
        System.out.println(board.toString());

        board.updateGame(WHITE);
        Move whiteKnightMoves = board.validatePlayersMove(new Position(7, 6), new Position(5, 5), WHITE);
        //board.makeMove(whiteKnightMoves); TODO: knight cant move legally when it should
        //assertTrue(board.getChessSquareState(new Position(5, 5)).state == ChessSquareState.State.OCCUPIED);
    }

    private Board setOpening() {
        Set whiteSet = new SetFactory.WhiteSetFactory().createSet();
        Set blackSet = new SetFactory.BlackSetFactory().createSet();
        Board board = new Board(whiteSet, blackSet);

        board.updateGame(WHITE);
        Move whitePawnMove = board.validatePlayersMove(new Position(6, 4), new Position(4, 4), WHITE);
        board.makeMove(whitePawnMove);

        board.updateGame(BLACK);
        Move blackPawnMove = board.validatePlayersMove(new Position(1, 4), new Position(3, 4), BLACK);
        board.makeMove(blackPawnMove);
        return board;
    }
}

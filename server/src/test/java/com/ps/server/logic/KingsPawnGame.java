package com.ps.server.Logic;

import org.junit.Test;
import static org.junit.Assert.*;


import static com.ps.server.Logic.Color.BLACK;
import static com.ps.server.Logic.Color.WHITE;

public class KingsPawnGame {

    @Test
    public void moveAndSquareStateCorrectness() {
        Set whiteSet = new SetFactory.WhiteSetFactory().createSet();
        Set blackSet = new SetFactory.BlackSetFactory().createSet();
        Board board = new Board(whiteSet, blackSet);
        System.out.print(board.toString());

        board.updateGame(WHITE);
        Move whitePawnMove = board.validatePlayersMove(new Position(6, 4), new Position(4, 4), WHITE);
        board.makeMove(whitePawnMove);
        assertTrue(board.getChessSquareState(new Position(4, 4)).state == ChessSquareState.State.OCCUPIED);
        System.out.println(board.toString());

        board.updateGame(BLACK);
        Move blackPawnMove = board.validatePlayersMove(new Position(1, 4), new Position(3, 4), BLACK);
        board.makeMove(blackPawnMove);
        assertTrue(board.getChessSquareState(new Position(3, 4)).state == ChessSquareState.State.OCCUPIED);
        System.out.println(board.toString());

        board.updateGame(WHITE);
        Move whitePawnMoveForward = board.validatePlayersMove(new Position(4, 4), new Position(3, 4), WHITE);
        assertTrue(whitePawnMoveForward == null);

        Move whitePawnLongMove = board.validatePlayersMove(new Position(6, 3), new Position(4, 3), WHITE);
        board.makeMove(whitePawnLongMove);
        assertTrue(board.getChessSquareState(new Position(4, 3)).state == ChessSquareState.State.OCCUPIED);
        System.out.println(board.toString());

        board.updateGame(BLACK);
        Move blackPawnCapture = board.validatePlayersMove(new Position(3, 4), new Position(4, 3), BLACK);
        board.makeMove(blackPawnCapture);
        assertTrue(board.getChessSquareState(new Position(4, 4)).state == ChessSquareState.State.OCCUPIED);
        assertTrue(board.getChessSquareState(new Position(3, 5)).state == ChessSquareState.State.EMPTY);
        System.out.println(board.toString());

        board.updateGame(WHITE);
        Move whiteQueenCapture = board.validatePlayersMove(new Position(7, 3), new Position(4, 3), WHITE);
        board.makeMove(whiteQueenCapture);
        assertTrue(board.getChessSquareState(new Position(7, 3)).state == ChessSquareState.State.EMPTY);
        System.out.println(board.toString());
    }
}

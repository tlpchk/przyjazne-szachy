package com.ps.server.Logic;

import com.ps.server.Logic.Pieces.Piece;
import org.junit.Test;

import static com.ps.server.Logic.Color.BLACK;
import static com.ps.server.Logic.Color.WHITE;
import static com.ps.server.Logic.Pieces.Piece.PieceType.KING;
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
        assertTrue(blackPinnedPawnMove == null);
    }

    @Test
    public void RuyLopez() {
        Board board = setOpening();
        System.out.println("Spanish opening");
        System.out.println(board.toString());

        board.updateGame(WHITE);
        Move whiteKnightMoves = board.validatePlayersMove(new Position(7, 6), new Position(5, 5), WHITE);
        board.makeMove(whiteKnightMoves);
        assertTrue(board.getChessSquareState(new Position(5, 5)).state == ChessSquareState.State.OCCUPIED);
        System.out.println(board.toString());

        board.updateGame(BLACK);
        Move blackKnightMoves = board.validatePlayersMove(new Position(0, 1), new Position(2, 2), BLACK);
        board.makeMove(blackKnightMoves);
        System.out.println(board.toString());

        board.updateGame(WHITE);
        Move whiteBishopMoves = board.validatePlayersMove(new Position(7, 5), new Position(3, 1), WHITE);
        board.makeMove(whiteBishopMoves);
        System.out.println(board.toString());

        board.updateGame(BLACK);
        Move blackPawnMoves = board.validatePlayersMove(new Position(1, 3), new Position(2, 3), BLACK);
        board.makeMove(blackPawnMoves);
        System.out.println(board.toString());

        board.updateGame(WHITE);
        Move anotherWhiteBishopMove = board.validatePlayersMove(new Position(3, 1), new Position(4, 0), WHITE);
        board.makeMove(anotherWhiteBishopMove);
        System.out.println(board.toString());

        board.updateGame(BLACK);
        Move pinnedKnight = board.validatePlayersMove(new Position(2, 2), new Position(4, 1), BLACK);
        assertTrue(pinnedKnight == null);
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

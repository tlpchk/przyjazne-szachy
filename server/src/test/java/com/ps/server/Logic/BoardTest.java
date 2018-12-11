package com.ps.server.Logic;

import com.ps.server.Logic.Pieces.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static com.ps.server.Logic.GameState.CHECKMATE;
import static com.ps.server.Logic.GameState.STALEMATE;
import static com.ps.server.Logic.Pieces.Piece.PieceType.*;
import static org.junit.Assert.*;

import static com.ps.server.Logic.Color.BLACK;
import static com.ps.server.Logic.Color.WHITE;
import static com.ps.server.Logic.Move.MoveType.EN_PASSANT;
import static com.ps.server.Logic.Move.MoveType.LONG_CASTLE;

public class BoardTest {
    Rook whiteRook = new Rook(WHITE, new Position(1, 4));
    Set whiteSet = new Set(WHITE, Arrays.asList(
            new King(WHITE, new Position(0, 4)),
            whiteRook
    ));

    Set blackSet = new Set(BLACK, Arrays.asList(
            new King(BLACK, new Position(7, 4)),
            new Rook(BLACK, new Position(6, 4))
    ));

    @Test
    public void test() {
        Board board = new Board(whiteSet, blackSet);
        System.out.print(board.toString());
        Move move = new Move(Piece.PieceType.ROOK, WHITE, new Position(1, 4), new Position(1, 3), Move.MoveType.NORMAL);
        assert (whiteRook.getPosition().equalsToPos(new Position(1, 4)));
        assert (!board.validateIfAfterMoveKingNotInCapture(move));
    }

    @Test
    public void test2() {
        Board board = new Board(whiteSet, blackSet);
        board.updateGame(WHITE);
        //whiteRook.getListOfMoves().stream().map(e -> e.dest.toString()).forEach(System.out::print);
        assert (board.validatePlayersMove(new Position(1, 4), new Position(2, 4), WHITE) != null);
        assert (board.validatePlayersMove(new Position(1, 4), new Position(1, 3), WHITE) == null);
    }

    @Test
    public void getListOfChanges() {
        assert (Board.getListOfChanges(new Move(PAWN, WHITE, new Position(1, 4), new Position(2, 3), EN_PASSANT))
                .stream()
                .filter(e -> e.eq(new Change(new Position(1, 3), null, null)))
                .findFirst()
                .orElse(null) != null);
    }

    @Test
    public void testLongCastle() {
        Set white = new Set(WHITE, Collections.singletonList(
                new King(WHITE, new Position(7, 4))
        ));
        Position blackKingsPos = SetFactory.BlackSetFactory.kingsPosition;
        Position blackRootPos = SetFactory.BlackSetFactory.rookPositions[0];
        King king = new King(BLACK, blackKingsPos);
        Set black = new Set(BLACK, Arrays.asList(
                king,
                new Rook(BLACK, blackRootPos)
        ));
        Board board = new Board(white, black);
        //System.out.print(board.toString());
        board.updateGame(BLACK);
        Move move = board.validatePlayersMove(blackKingsPos, blackRootPos, BLACK);
        board.makeMove(move);
        //System.out.print(board.toString());
        assert(king.getPosition().equalsToPos(new Position(0, 2)));
    }

    @Test
    public void testPromotionMove() {
            Set black = new Set(BLACK, Arrays.asList(
                    new King(BLACK, new Position(0, 0))
            ));
            Set white = new Set(WHITE, Arrays.asList(
                    new King(WHITE, new Position(6, 2)),
                    new Pawn(WHITE, new Position(1, 5))
            ));
            Board board = new Board(white, black);
            board.updateGame(WHITE);
            Move move = board.validatePlayersMove(new Position(1, 5), new Position(0,5), WHITE);
            move.setPromoteTo(KNIGHT);
            board.makeMove(move);
            assertEquals(KNIGHT, board.getChessSquareState(new Position(0,5)).getPiece().type);
    }

    @Test
    public void testGameEnd() {
        Set white = new Set(WHITE, Arrays.asList(
                new King(WHITE, new Position(0, 0)),
                new Rook(WHITE, new Position(2, 5))
        ));
        Set black = new Set(BLACK, Arrays.asList(
                new King(BLACK, new Position(6, 2)),
                new Queen(BLACK, new Position(1, 1)),
                new Bishop(BLACK, new Position(3, 3))
        ));
        Board board = new Board(white, black);
        assertEquals(CHECKMATE, board.updateGame(WHITE));

    }

    @Test
    public void testStaleMate() {
        Set black = new Set(BLACK, Arrays.asList(
                new King(BLACK, new Position(0, 0)),
                new Pawn(BLACK, new Position(2, 3))
        ));
        Set white = new Set(WHITE, Arrays.asList(
                new King(WHITE, new Position(6, 2)),
                new Rook(WHITE, new Position(1, 1)),
                new Bishop(WHITE, new Position(3, 3))
        ));
        Board board = new Board(white, black);
        assertEquals(STALEMATE, board.updateGame(BLACK));

    }
}
package com.ps.server.Logic;

import com.ps.server.Logic.Pieces.Bishop;
import com.ps.server.Logic.Pieces.King;
import com.ps.server.Logic.Pieces.Pawn;
import org.junit.Test;

import java.util.Arrays;

import static com.ps.server.Logic.Color.BLACK;
import static com.ps.server.Logic.Color.WHITE;

public class ChessEngineTest {

    boolean testMakingMove(Board board, Position loc, Position dest, Color turn) {
        //first in set needs always to be the king
        board.updateGame(turn);
        return board.validatePlayersMove(loc, dest, turn) != null;
    }

    @Test
    public void testEnPassantMove() {
        Set whiteSet = new Set( WHITE,
                Arrays.asList(
                        new King(WHITE, new Position(7, 4)),
                        new Pawn(WHITE, new Position(3, 5))
                )
        );

        Set blackSet = new Set( BLACK,
                Arrays.asList(
                        new King(BLACK, new Position(0, 4)),
                        new Pawn(BLACK, new Position(1, 4))
                )
        );

        Board board = new Board(whiteSet, blackSet);
        board.updateGame(BLACK);
        Move longMove = board.validatePlayersMove(new Position(1, 4), new Position(2, 4), BLACK);
        board.makeMove(longMove);
        assert(testMakingMove(board, new Position(3, 5), new Position(2, 4), WHITE));
        assert(!testMakingMove(board, new Position(3, 5), new Position(2, 6), WHITE));
    }

    @Test
    public  void regularBishopMove() {
        Set whiteSet = new Set( WHITE,
                Arrays.asList(
                        new King(WHITE, new Position(7, 4)),
                        new Bishop(WHITE, new Position(6, 7))
                )
        );

        Set blackSet = new Set( BLACK,
                Arrays.asList(
                        new King(BLACK, new Position(0, 4))
                )
        );

        Board board = new Board(whiteSet, blackSet);
        assert(testMakingMove(board, new Position(6,7), new Position(2, 3), WHITE));
    }

    @Test
    public void moveAfterSetUp() {
        Set whiteset = new SetFactory.WhiteSetFactory().createSet();
        Set blackset = new SetFactory.BlackSetFactory().createSet();

        Board board = new Board(whiteset, blackset);

        testMakingMove(board, new Position(7, 1), new Position(5, 2), WHITE);
    }
}

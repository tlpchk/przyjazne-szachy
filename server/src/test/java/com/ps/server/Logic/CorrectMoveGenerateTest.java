package com.ps.server.Logic;
import com.ps.server.Logic.Pieces.King;
import com.ps.server.Logic.Pieces.Pawn;
import com.ps.server.Logic.Pieces.Rook;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.ps.server.Logic.Color.BLACK;
import static com.ps.server.Logic.Color.WHITE;
import static org.junit.Assert.assertTrue;

public class CorrectMoveGenerateTest {
    @Test
    public void whiteGeneratedMoves() {
        Board board = setBoard();
        System.out.println(board.toString());

        board.updateGame(WHITE);
        List<Move> legalMoves = board.getLegalMoves(Color.WHITE);

        for(Move move : legalMoves) {
            System.out.println(move.loc + " " + move.dest);
        }

        assertTrue(legalMoves.size() == 11);
    }

    @Test
    public void blackGeneratedMoves() {
        Board board = setBoard();
        System.out.println(board.toString());

        board.updateGame(BLACK);
        List<Move> legalMoves = board.getLegalMoves(Color.BLACK);

        for(Move move : legalMoves) {
            System.out.println(move.loc + " " + move.dest);
        }

        assertTrue(legalMoves.size() == 9); //TODO rook moves aren't generated correctly, the rook should be able to Move to [4,4], [3,4], [2,4] and [1,4]
    }

    private Board setBoard() {
        Set whiteSet = new Set( WHITE,
                        Arrays.asList(
                        new King(WHITE, new Position(0, 4)),
                        new Pawn(WHITE, new Position(6, 5)),
                        new Rook(WHITE, new Position(1, 4))
                )
        );

        Set blackSet = new Set( BLACK,
                        Arrays.asList(
                        new King(BLACK, new Position(7, 4)),
                        new Rook(BLACK, new Position(5, 4))
                )
        );

        Board board = new Board(whiteSet, blackSet);
        return board;
    }
}

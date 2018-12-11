package com.ps.server.AI;

import com.ps.server.Logic.*;
import com.ps.server.Logic.Pieces.King;
import com.ps.server.Logic.Pieces.Pawn;
import com.ps.server.Logic.Pieces.Rook;
import org.junit.Test;

import java.util.Arrays;

import static com.ps.server.Logic.Color.BLACK;
import static com.ps.server.Logic.Color.WHITE;
import static org.junit.Assert.assertEquals;

public class MultipleBotMoveTest {
    @Test
    public void test() {
        Rook whiteRook = new Rook(WHITE, new Position(1, 4));
        Pawn whitePawn = new Pawn(WHITE, new Position(6, 5));

        Set whiteSet = new Set(WHITE, Arrays.asList(
                new King(WHITE, new Position(0, 4)),
                whiteRook,
                whitePawn
        ));

        Set blackSet =  new Set(BLACK, Arrays.asList(
                new King(BLACK, new Position(7, 4)),
                new Rook(BLACK, new Position(5, 4))
        ));

        Board board = new Board(whiteSet, blackSet);

        board.updateGame(Color.WHITE);
        System.out.print(board.toString());

        for(Move m : board.getLegalMoves(WHITE)) {
            System.out.println(m.loc + " " + m.dest);
        }

        MoveStrategy moveStrategy = new AlphaBeta();
        Move move = moveStrategy.execute(board, 4, Color.WHITE);

        board.makeMove(move);
        assertEquals(move.dest.col, 4);
        assertEquals(move.dest.row, 5);

        board.updateGame(Color.BLACK);
        System.out.println(board.toString());

        for(Move m : board.getLegalMoves(BLACK)) {
            System.out.println(m.loc + " " + m.dest);
        }

        Move blackKingMove = board.validatePlayersMove(new Position(7, 4), new Position(6, 4), BLACK);
        board.makeMove(blackKingMove);

        board.updateGame(Color.WHITE);
        System.out.println(board.toString());

        for(Move m : board.getLegalMoves(WHITE)) {
            System.out.println(m.loc + " " + m.dest);
        }

        Move whiteRookMoves = board.validatePlayersMove(new Position(1, 4), new Position(1, 0), WHITE);
        board.makeMove(whiteRookMoves);

        board.updateGame(Color.BLACK);
        System.out.println(board.toString());

        for(Move m : board.getLegalMoves(BLACK)) {
            System.out.println(m.loc + " " + m.dest);
        }

        //Move anotherBotMove = moveStrategy.execute(board, 4, BLACK);
        //board.makeMove(anotherBotMove);

        //System.out.println(board.toString());
    }

    @Test
    public void test2() {
        Set blackSet = new Set(BLACK, Arrays.asList(
                new King(BLACK, new Position(0, 4)),
                new Rook(BLACK, new Position(1, 0)),
                new Pawn(BLACK, new Position(5, 4))
        ));

        Set whiteSet =  new Set(WHITE, Arrays.asList(
                new King(WHITE, new Position(6, 4))
        ));

        Board board = new Board(whiteSet, blackSet);
        System.out.println(board.toString());

        board.updateGame(WHITE);
        MoveStrategy moveStrategy = new AlphaBeta();
        Move move = moveStrategy.execute(board, 4, WHITE);

        board.makeMove(move);
        assertEquals(move.dest.col, 4);
        assertEquals(move.dest.row, 5);

        System.out.println(board.toString());
        System.out.println(((AlphaBeta) moveStrategy).getBoardsEvaluated());
    }
}

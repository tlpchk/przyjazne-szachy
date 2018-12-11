package com.ps.server.AI;

import com.ps.server.Logic.*;
import com.ps.server.Logic.Pieces.King;
import com.ps.server.Logic.Pieces.Pawn;
import com.ps.server.Logic.Pieces.Rook;
import org.junit.Test;

import java.util.Arrays;

import static com.ps.server.Logic.Color.BLACK;
import static com.ps.server.Logic.Color.WHITE;

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

        MoveStrategy moveStrategy = new AlphaBeta();
        ExtendedBoardEvaluator extendedBoardEvaluator = new ExtendedBoardEvaluator();
        Move move = moveStrategy.execute(board, 4, Color.WHITE);

        board.makeMove(move);

        board.updateGame(Color.BLACK);
        System.out.println(board.toString());

        Move blackKingMove = board.validatePlayersMove(new Position(7, 4), new Position(6, 4), BLACK);
        board.makeMove(blackKingMove);

        System.out.println(board.toString());

        board.updateGame(Color.WHITE);
        Move whiteRookMove = board.validatePlayersMove(new Position(1, 4), new Position(1, 0), WHITE);
        board.makeMove(whiteRookMove);

        System.out.println(board.toString());

        board.updateGame(Color.BLACK);

        Move anotherBotMove = moveStrategy.execute(board, 4, Color.BLACK);
        System.out.println(anotherBotMove.dest);
        System.out.println(extendedBoardEvaluator.evaluate(board));
        board.makeMove(anotherBotMove);

        System.out.println(board.toString());
        /*Move blackCapture = board.validatePlayersMove(new Position(6, 4), new Position(5, 4), BLACK);
        board.makeMove(blackCapture);
        System.out.println(extendedBoardEvaluator.evaluate(board));

        System.out.println(board.toString());*/
    }
}

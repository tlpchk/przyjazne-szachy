package com.ps.server.Logic;

import com.ps.server.Logic.Pieces.*;
import org.junit.Test;

import java.util.Arrays;

import static com.ps.server.Logic.Color.BLACK;
import static com.ps.server.Logic.Color.WHITE;
import static com.ps.server.Logic.Move.MoveType.EN_PASSANT;
import static com.ps.server.Logic.Pieces.Piece.PieceType.PAWN;

public class BoardTest {
    Rook whiteRook = new Rook(WHITE, new Position(1, 4));
    Set whiteSet = new Set(WHITE, Arrays.asList(
            new King(WHITE, new Position(0, 4)),
            whiteRook
    ));

    Set blackSet =  new Set(BLACK, Arrays.asList(
            new King(BLACK, new Position(7, 4)),
            new Rook(BLACK, new Position(6, 4))
    ));

    @Test
    public void test() {
        Board board = new Board(whiteSet, blackSet);
        System.out.print(board.toString());
        Move move = new Move(Piece.PieceType.ROOK, WHITE, new Position(1,4), new Position(1, 3), Move.MoveType.NORMAL);
        assert(whiteRook.getPosition().equalsToPos(new Position(1, 4)));
        assert(!board.validateIfAfterMoveKingNotInCapture(move));
    }

    @Test
    public void test2() {
        Board board = new Board(whiteSet, blackSet);
        board.updateGame(WHITE);
        //whiteRook.getListOfMoves().stream().map(e -> e.dest.toString()).forEach(System.out::print);
        assert(board.validatePlayersMove(new Position(1, 4), new Position(2, 4), WHITE) != null);
        assert(board.validatePlayersMove(new Position(1, 4), new Position(1, 3), WHITE) == null);
    }

    @Test
    public void getListOfChanges() {
        assert(Board.getListOfChanges(new Move(PAWN, WHITE, new Position(1, 4), new Position(2, 3), EN_PASSANT))
                .stream()
                .filter(e -> e.eq(new Change(new Position(1,3), null,  null)))
                .findFirst()
                .orElse(null) != null);
    }
}
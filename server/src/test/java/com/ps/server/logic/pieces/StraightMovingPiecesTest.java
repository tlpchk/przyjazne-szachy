package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.Board;
import com.ps.server.Logic.Position;
import com.ps.server.Logic.Set;
import org.junit.Test;

import java.util.Arrays;

import static com.ps.server.Logic.Color.BLACK;
import static com.ps.server.Logic.Color.WHITE;
import static org.junit.Assert.*;

public class StraightMovingPiecesTest {
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
        assert(whiteRook.checkIfSomethingBetween(new Position(7, 4)));
    }

}
package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.*;
import org.junit.Test;

import static com.ps.server.Logic.Color.WHITE;

public class BishopTest {
    Bishop bishop = new Bishop(WHITE, new Position(2,2));

    @Test
    public void name() {
        bishop.addBoard(new Board(new Set(WHITE, null), new Set(WHITE, null)));
        assert(bishop.semiLegalMoves().size() == 11);
    }
}
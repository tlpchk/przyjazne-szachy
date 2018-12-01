package com.ps.server.logic.pieces;

import com.ps.server.logic.*;
import org.junit.Test;

import static com.ps.server.logic.Color.WHITE;

public class BishopTest {
    Bishop bishop = new Bishop(WHITE, new Position(2,2));

    @Test
    public void name() {
        bishop.addBoard(new Board(new Set(WHITE, null), new Set(WHITE, null)));
        assert(bishop.semiLegalMoves().size() == 11);
    }
}
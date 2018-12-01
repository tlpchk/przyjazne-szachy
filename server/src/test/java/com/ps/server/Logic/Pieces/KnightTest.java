package com.ps.server.logic.pieces;

import com.ps.server.logic.*;
import org.junit.Test;

import static com.ps.server.logic.Color.WHITE;

public class KnightTest {
    Knight knight = new Knight(WHITE, new Position(0,1));

    @Test
    public void name() {
        knight.addBoard(new Board(new Set(WHITE, null), new Set(WHITE, null)));
        assert(knight.semiLegalMoves().size() == 3);
    }
}

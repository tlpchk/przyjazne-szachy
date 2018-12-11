package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.*;
import org.junit.Test;

import static com.ps.server.Logic.Color.BLACK;
import static com.ps.server.Logic.Color.WHITE;

public class KnightTest {
    Knight knight = new Knight(WHITE, new Position(0,1));

    @Test
    public void name() {
        knight.addBoard(new Board(new Set(WHITE, null), new Set(BLACK, null)));
        assert(knight.semiLegalMoves().size() == 3);
    }
}

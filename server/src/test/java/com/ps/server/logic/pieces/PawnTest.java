package com.ps.server.logic.pieces;

import com.ps.server.logic.*;
import org.junit.Test;

import static com.ps.server.logic.Color.WHITE;

public class PawnTest {
    Pawn pawn = new Pawn(WHITE, new Position(2,2));

    @Test
    public void name() {
        pawn.addBoard(new Board(new Set(WHITE, null), new Set(WHITE, null)));

        assert(pawn.semiLegalMoves().size() == 2);
    }
}
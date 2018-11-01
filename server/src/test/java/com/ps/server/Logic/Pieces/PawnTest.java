package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.Color;
import com.ps.server.Logic.Move;
import com.ps.server.Logic.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class PawnTest {
    Pawn pawn = new Pawn(Color.WHITE);

    @Test
    public void movePawnTest() {
        assert(pawn.checkMove(new Position(6, 7), new Position(5, 7)));
    }

}
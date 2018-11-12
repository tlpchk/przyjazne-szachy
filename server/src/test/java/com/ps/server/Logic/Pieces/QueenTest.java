package com.ps.server.Logic.Pieces;

import com.ps.server.Logic.Color;
import com.ps.server.Logic.Position;
import org.junit.Test;

public class QueenTest {
    Queen queen = new Queen(Color.WHITE);

    @Test
    public void moveQueenTest() {
        assert(queen.checkMove(new Position(7, 3), new Position(4, 0)));
    }

}
package com.ps.server.AI;

import com.ps.server.Logic.Board;
import com.ps.server.Logic.Color;

public class BotController {
    public void execute(Board board, Color color) {
        MoveStrategy moveStrategy = new AlphaBeta();
        moveStrategy.execute(board, getDepth(), color);
    }

    //TODO determine how to calculate depth based on time left
    private int getDepth() {
        return 4; //temporarily fixed depth
    }
}

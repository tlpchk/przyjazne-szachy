package com.ps.server.AI;

import com.ps.server.Logic.Board;
import com.ps.server.Logic.Color;
import com.ps.server.Logic.Move;

public class BotController {
    int movesCounter = 0;

    //TODO think when opening phase should end to switch to other evaluation method
    public Move execute(Board board, Color color) {
        if(movesCounter >= 6) {
            ExtendedBoardEvaluator extendedBoardEvaluator = new ExtendedBoardEvaluator();
            MoveStrategy moveStrategy = new AlphaBeta(extendedBoardEvaluator);
            Move move = moveStrategy.execute(board, getDepth(), color);
            movesCounter++;
            return move;
        }
        else {
            OpeningBoardEvaluator openingBoardEvaluator = new OpeningBoardEvaluator();
            MoveStrategy moveStrategy = new AlphaBeta(openingBoardEvaluator);
            Move move = moveStrategy.execute(board, getDepth(), color);
            movesCounter++;
            return move;
        }
    }

    //TODO determine how to calculate depth based on time left
    private int getDepth() {
        return 4; //temporarily fixed depth
    }
}

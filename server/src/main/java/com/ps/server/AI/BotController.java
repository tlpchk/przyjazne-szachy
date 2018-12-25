package com.ps.server.AI;

import com.ps.server.Logic.Board;
import com.ps.server.Logic.Color;
import com.ps.server.Logic.Move;

public class BotController {
    private MoveStrategy openingMoveStrategy;
    private MoveStrategy middleMoveStrategy;

    private int movesCounter;

    public BotController() {
        OpeningBoardEvaluator openingBoardEvaluator = new OpeningBoardEvaluator();
        openingMoveStrategy = new AlphaBeta(openingBoardEvaluator);

        ExtendedBoardEvaluator extendedBoardEvaluator = new ExtendedBoardEvaluator();
        middleMoveStrategy = new AlphaBeta(extendedBoardEvaluator);

        movesCounter = 0;
    }

    //TODO think when opening phase should end to switch to other evaluation method
    public Move execute(Board board, Color color) {
        movesCounter++;

        if(movesCounter >= 6) {
            Move move = this.middleMoveStrategy.execute(board, getDepth(), color);
            return move;
        }
        else {
            Move move = this.openingMoveStrategy.execute(board, getDepth(), color);
            return move;
        }
    }

    //TODO determine how to calculate depth based on time left
    private int getDepth() {
        return 4; //temporarily fixed depth
    }
}

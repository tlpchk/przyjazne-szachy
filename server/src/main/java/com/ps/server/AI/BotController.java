package com.ps.server.AI;

import com.ps.server.Logic.Board;
import com.ps.server.Logic.Color;
import com.ps.server.Logic.Move;

/**
 * Controller class used by others to make AI move
 */
public class BotController {
    private MoveStrategy openingMoveStrategy;
    private MoveStrategy middleMoveStrategy;

    private int movesCounter;

    /**
     * Constructor, sets evaluators up
     */
    public BotController() {
        OpeningBoardEvaluator openingBoardEvaluator = new OpeningBoardEvaluator();
        openingMoveStrategy = new AlphaBeta(openingBoardEvaluator);

        ExtendedBoardEvaluator extendedBoardEvaluator = new ExtendedBoardEvaluator();
        middleMoveStrategy = new AlphaBeta(extendedBoardEvaluator);

        movesCounter = 0;
    }

    /**
     * AI move execution
     * @param board Board instance
     * @param color bot color
     * @return move that Ai wants to play
     */
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

    /**
     * Determines depth of searching in game tree
     * @return initial evaluation depth
     */
    //TODO determine how to calculate depth based on time left
    private int getDepth() {
        return 4; //temporarily fixed depth
    }
}

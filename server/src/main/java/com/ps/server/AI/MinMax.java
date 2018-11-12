package com.ps.server.AI;

import com.ps.server.Logic.Board;
import com.ps.server.Logic.Move;

public class MinMax implements MoveStrategy {
    private final BoardEvaluator boardEvaluator;

    public MinMax() {
        this.boardEvaluator = new StandardBoardEvaluator();
    }

    @Override
    public String toString() {
        return "MinMax";
    }

    @Override
    public Move execute(Board board, int depth) {
        return null;
    }
}

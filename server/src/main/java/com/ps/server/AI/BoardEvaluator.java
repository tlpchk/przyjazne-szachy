package com.ps.server.AI;

import com.ps.server.Logic.Board;

/**
 * Interface for board evaluation methods
 */
public interface BoardEvaluator {
    int evaluate(final Board board);
}

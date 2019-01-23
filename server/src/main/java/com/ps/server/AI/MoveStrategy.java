package com.ps.server.AI;

import com.ps.server.Logic.Board;
import com.ps.server.Logic.Color;
import com.ps.server.Logic.Move;

/**
 * Interface of different strategies to determine move to play
 */
public interface MoveStrategy {
    Move execute(Board board, int depth, Color color);
}

package com.ps.server.AI;

import com.ps.server.Logic.Board;
import com.ps.server.Logic.Move;

public interface MoveStrategy {
    Move execute(Board board, int depth);
}

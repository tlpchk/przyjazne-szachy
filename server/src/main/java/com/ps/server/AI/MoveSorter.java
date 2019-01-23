package com.ps.server.AI;

import com.ps.server.Logic.Board;
import com.ps.server.Logic.Color;
import com.ps.server.Logic.Move;
import com.ps.server.Logic.Pieces.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static com.ps.server.Logic.Color.BLACK;
import static com.ps.server.Logic.Color.WHITE;

//TODO implement rest of code
public class MoveSorter {
    public List<Move> sort(Board board, Color color) {
        Color otherColor = (color == WHITE) ?  BLACK : WHITE;
        Map<Move, Integer> movesValues = new HashMap();
        board.updateGame(color);

        for(Move m : board.getLegalMoves(color)) {
            Board boardCopy = board.copy();
            boardCopy.makeMove(m);

            if(boardCopy.checkIfKingInCapture(color) == true) {
                movesValues.put(m, 10);
                continue;
            }

            for(Piece p : board.getSet(otherColor).getPieceSet())
            {
                if(p.getPosition().row == m.dest.row && p.getPosition().col == m.dest.col) {
                    movesValues.put(m, 8);
                    continue;
                }
            }

            movesValues.put(m, 1);
        }

        return null;
    }
}

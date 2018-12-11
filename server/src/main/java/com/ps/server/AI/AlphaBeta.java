package com.ps.server.AI;

import com.ps.server.Logic.Board;
import com.ps.server.Logic.Color;
import com.ps.server.Logic.Move;
import com.ps.server.Logic.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class AlphaBeta implements MoveStrategy {
    private final BoardEvaluator boardEvaluator;
    private int boardsEvaluated;

    public AlphaBeta() {
        boardEvaluator = new ExtendedBoardEvaluator();
        boardsEvaluated = 0;
    }

    public int getBoardsEvaluated() {
        return boardsEvaluated;
    }

    //TODO: Fix Game update, think about board copies
    @SuppressWarnings("Duplicates") //temporary
    @Override
    public Move execute(Board board, int depth, Color color) {
        boardsEvaluated = 0;
        final long startTime = System.currentTimeMillis();

        Move bestMove = null;

        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        int currentValue;
        Color otherColor;

        if(color == Color.WHITE)
            otherColor = Color.BLACK;
        else
            otherColor = Color.WHITE;

        System.out.println("Thinking with depth " + depth);

        for(Move move : board.getLegalMoves(color)) {
            Board chessBoard = board.copy();
            chessBoard.makeMove(move);

            if (color == Color.WHITE) {
                currentValue = min(chessBoard, depth - 1, maxValue, minValue, otherColor);
            }
            else {
                currentValue = max(chessBoard, depth - 1, maxValue, minValue, otherColor);
            }

            if(color == Color.WHITE && currentValue > maxValue) {
                maxValue = currentValue;
                bestMove = move;
            }
            else if(color == Color.BLACK && currentValue < minValue) {
                minValue = currentValue;
                bestMove = move;
            }
        }

        final long executionTime = System.currentTimeMillis() - startTime;

        return bestMove;
    }

    @SuppressWarnings("Duplicates")
    public int min(final Board board, final int depth, int highest, int lowest, Color color) {
        board.updateGame(color);

        if(depth == 0 /*TODO checkmate*/) {
            this.boardsEvaluated++;
            return this.boardEvaluator.evaluate(board);
        }

        int currentLowest = lowest;
        Color otherColor;

        if(color == Color.WHITE)
            otherColor = Color.BLACK;
        else
            otherColor = Color.WHITE;

        for(final Move move : board.getLegalMoves(color)) {
            Board chessBoard = board.copy();
            chessBoard.makeMove(move);
            currentLowest = Math.min(currentLowest, max(chessBoard, depth - 1, highest, currentLowest, otherColor));

            if(currentLowest <= highest) {
                return highest;
            }
        }

        return currentLowest;
    }

    @SuppressWarnings("Duplicates")
    public int max(final Board board, final int depth, int highest, int lowest, Color color) {
        board.updateGame(color);

        if(depth == 0 /*TODO checkmate*/) {
            this.boardsEvaluated++;
            return this.boardEvaluator.evaluate(board);
        }

        int currentHighest = highest;
        Color otherColor;

        if(color == Color.WHITE)
            otherColor = Color.BLACK;
        else
            otherColor = Color.WHITE;

        for(final Move move : board.getLegalMoves(color)) {
            Board chessBoard = board.copy();
            chessBoard.makeMove(move);
            currentHighest = Math.max(currentHighest, min(chessBoard, depth - 1, currentHighest, lowest, otherColor));

            if(currentHighest >= lowest) {
                return lowest;
            }
        }

        return currentHighest;
    }
}

package com.ps.server.AI;

import com.ps.server.Logic.Board;
import com.ps.server.Logic.Color;
import com.ps.server.Logic.Move;

/**
 * Basic method for finding moves to play by AI
 */
public class MinMax implements MoveStrategy {
    private final BoardEvaluator boardEvaluator;
    private int boardsEvaluated;

    /**
     * Constructor, setting up method of evaluation
     */
    public MinMax() {
        this.boardEvaluator = new StandardBoardEvaluator();
        boardsEvaluated = 0;
    }

    /**
     * Name of class
     * @return name of this class
     */
    @Override
    public String toString() {
        return "MinMax";
    }

    /**
     * Getter for boardEvaluated field
     * @return number of boards that were evaluated
     */
    public int getBoardsEvaluated() {
        return boardsEvaluated;
    }

    /**
     * Place to call minimalizing and maximizing methods, based on player color
     * @param board Board instance
     * @param depth how many moves ahead are to consider
     * @param color player color
     * @return best move found during reviewing game tree on given depth
     */
    @SuppressWarnings("Duplicates")
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

            if(color == Color.WHITE) {
                currentValue = min(chessBoard, depth - 1, otherColor);
            }
            else {
                currentValue = max(chessBoard, depth - 1, otherColor);
            }

            if(color == Color.WHITE && currentValue >= maxValue) {
                maxValue = currentValue;
                bestMove = move;
            }
            else if(color == Color.BLACK && currentValue <= minValue) {
                minValue = currentValue;
                bestMove = move;
            }
        }

        final long executionTime = System.currentTimeMillis() - startTime;

        return bestMove;
    }

    /**
     * Level of minimization
     * @param board Board instance
     * @param depth current depth
     * @param color player color
     * @return minimal value found on given level
     */
    public int min(final Board board, final int depth, Color color) {
        board.updateGame(color);

        if(depth == 0 /*TODO checkmate*/) {
            boardsEvaluated++;
            return this.boardEvaluator.evaluate(board);
        }

        int minValue = Integer.MAX_VALUE;
        Color otherColor;

        if(color == Color.WHITE)
            otherColor = Color.BLACK;
        else
            otherColor = Color.WHITE;

        for(final Move move : board.getLegalMoves(color)) {
            Board chessBoard = board.copy();
            chessBoard.makeMove(move);
            final int currentValue = max(chessBoard, depth - 1, otherColor);

            if(currentValue <= minValue)
                minValue = currentValue;
        }

        return minValue;
    }

    /**
     * Level of maximization
     * @param board Board instance
     * @param depth current depth
     * @param color player color
     * @return maximum value found on given level
     */
    public int max(final Board board, final int depth, Color color) {
        board.updateGame(color);

        if(depth == 0 /*TODO checkmate*/) {
            boardsEvaluated++;
            return this.boardEvaluator.evaluate(board);
        }

        int maxValue = Integer.MIN_VALUE;
        Color otherColor;

        if(color == Color.WHITE)
            otherColor = Color.BLACK;
        else
            otherColor = Color.WHITE;

        for(final Move move : board.getLegalMoves(color)) {
            Board chessBoard = board.copy();
            chessBoard.makeMove(move);
            final int currentValue = min(chessBoard, depth - 1, otherColor);

            if(currentValue >= maxValue)
                maxValue = currentValue;
        }

        return maxValue;
    }
}

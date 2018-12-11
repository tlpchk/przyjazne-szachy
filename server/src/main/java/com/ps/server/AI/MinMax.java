package com.ps.server.AI;

import com.ps.server.Logic.Board;
import com.ps.server.Logic.Color;
import com.ps.server.Logic.Move;
import com.ps.server.Logic.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class MinMax implements MoveStrategy {
    private final BoardEvaluator boardEvaluator;
    private int boardsEvaluated;

    public MinMax() {
        this.boardEvaluator = new StandardBoardEvaluator();
        boardsEvaluated = 0;
    }

    @Override
    public String toString() {
        return "MinMax";
    }

    public int getBoardsEvaluated() {
        return boardsEvaluated;
    }

    @Override
    public Move execute(Board board, int depth, Color color) {
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

    public int min(final Board board, final int depth, Color color) {
        //System.out.println("1");
        //System.out.print(board.toString());
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
            final int currentValue = max(board, depth - 1, otherColor);

            if(currentValue <= minValue)
                minValue = currentValue;
        }

        return minValue;
    }

    public int max(final Board board, final int depth, Color color) {
        //System.out.println("2");
        //System.out.print(board.toString());
        board.updateGame(color);

        if(depth == 0 /*TODO checkmate*/) {
            boardsEvaluated++;
            System.out.println(boardsEvaluated);
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
            final int currentValue = min(board, depth - 1, otherColor);

            if(currentValue >= maxValue)
                maxValue = currentValue;
        }

        return maxValue;
    }
}

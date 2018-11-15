package com.ps.server.AI;

import com.ps.server.Logic.Board;
import com.ps.server.Logic.Color;
import com.ps.server.Logic.Move;
import com.ps.server.Logic.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

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

        for(Move move : getLegalMoves(board, color)) {
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
        if(depth == 0 /*TODO checkmate*/)
            return this.boardEvaluator.evaluate(board);

        int minValue = Integer.MAX_VALUE;
        Color otherColor;

        if(color == Color.WHITE)
            otherColor = Color.BLACK;
        else
            otherColor = Color.WHITE;


        for(final Move move : getLegalMoves(board, color)) {
            board.makeMove(move);
            final int currentValue = max(board, depth - 1, otherColor);

            if(currentValue <= minValue)
                minValue = currentValue;
        }


        return minValue;
    }

    public int max(final Board board, final int depth, Color color) {
        if(depth == 0 /*TODO checkmate*/)
            return this.boardEvaluator.evaluate(board);

        int maxValue = Integer.MIN_VALUE;
        Color otherColor;

        if(color == Color.WHITE)
            otherColor = Color.BLACK;
        else
            otherColor = Color.WHITE;

        for(final Move move : getLegalMoves(board, color)) {
            board.makeMove(move);
            final int currentValue = min(board, depth - 1, otherColor);

            if(currentValue >= maxValue)
                maxValue = currentValue;
        }

        return maxValue;
    }

    public List<Move> getLegalMoves(final Board board, Color color) {
        Piece[][] chessBoard = board.getBoard();
        List<Move> legalMoves = new ArrayList<>();

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(chessBoard[i][j] == null)
                    continue;
                if(chessBoard[i][j].color == color && chessBoard[i][j].getListOfMoves() != null) {
                    legalMoves.addAll(chessBoard[i][j].getListOfMoves());
                }
            }
        }

        return legalMoves;
    }
}

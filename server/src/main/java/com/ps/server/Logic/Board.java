package com.ps.server.Logic;
import com.ps.server.Logic.Pieces.*;
import static com.ps.server.Logic.Color.*;

public class Board {
    private ChessSquare[][] board;
    final static int ROW_NUM = 8;
    final static int COL_NUM = 8;

    Board() {
        board = generateBoard();
        initChessSquares();
    }

    private void initChessSquares() {
        for(int row = 0; row < ROW_NUM; row++) {
            for(int col = 0; col < COL_NUM; col++) {
                board[row][col].makeMoveList();
            }
        }
    }

    public ChessSquare[][] getBoard() {
        return board;
    }

    private ChessPiece generateChessPieceForSquare(int row, int column) {
        if(row > 1 && row < 6) return null;
        Color color = (row < 4) ? BLACK : WHITE;
        if(row == 1 || row == 6) return new Pawn(color);

        switch(column) {
            case 0:
            case 7: return new Rook(color);
            case 1:
            case 6: return new Knight(color);
            case 2:
            case 5: return new Bishop(color);
            case 3: return new Queen(color);
            case 4: return new King(color);
        }
        return null;
    }

    private ChessSquare[][] generateBoard() {
        ChessSquare[][] board = new ChessSquare[ROW_NUM][COL_NUM];
        for(int row = 0; row < ROW_NUM; row++) {
            for(int col = 0; col < COL_NUM; col++) {
                board[row][col] = new ChessSquare(
                                            generateChessPieceForSquare(row, col),
                                            new Position(row, col),
                                            this
                                        );
            }
        }
        return board;
    }

    boolean makeMove(Move move) {
        //TODO:: check king state
        Position loc = move.loc;
        Position dest = move.dest;
        boolean out = board[loc.row][loc.col].makeMove(board[dest.row][dest.col], move.color);
        //TODO:: check game state
        return out;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(" ");
        for(int col = 0; col < COL_NUM; col++) {
            builder.append("|").append(col);
        }
        builder.append("|\n");
        for(int row = 0; row < ROW_NUM; row++) {
            builder.append(row);
            for(int col = 0; col < COL_NUM; col++) {
                builder.append("|").append(board[row][col].toString());
            }
            builder.append("|\n");
        }
        return builder.toString();
    }
}

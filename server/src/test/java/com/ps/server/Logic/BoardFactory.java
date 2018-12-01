package com.ps.server.Logic;

import com.ps.server.Logic.Pieces.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.ps.server.Logic.Color.BLACK;
import static com.ps.server.Logic.Color.WHITE;

public class BoardFactory {

    private static Piece getPiece(String s, int row, int col) {
        Position position = new Position(row, col);
        switch(s) {
            case "♖": return new Rook(BLACK, position);
            case "♘": return new Knight(BLACK, position);
            case "♗": return new Bishop(BLACK, position);
            case "♕": return new Queen(BLACK, position);
            case "♔": return new King(BLACK, position);
            case "♙": return new Pawn(BLACK, position);
            case "♜": return new Rook(WHITE, position);
            case "♞": return new Knight(WHITE, position);
            case "♝": return new Bishop(WHITE, position);
            case "♛": return new Queen(WHITE, position);
            case "♚": return new King(WHITE, position);
            case "♟": return new Pawn(WHITE, position);
            case "_": return null;
        }
        return null;
    }

    static Board createBoard(List<String> board) {
        if(board.size() != 9)
            throw new RuntimeException("Wrong board size");
        Pattern pattern = Pattern.compile("[♖♘♗♕♔♙♜♞♝♛♚♟_]");
        List<List<String>> pawns = board
                .stream()
                .map(e -> Arrays.stream(e.split("[|]"))
                        .filter(pattern.asPredicate())
                        .collect(Collectors.toList()))
                .filter(e -> e.size() != 0)
                .collect(Collectors.toList());
        pawns.forEach(e -> {
            if(e.size() != 8) {
                throw new RuntimeException("Wrong board size");
            }
        });

        List<Piece> whiteSet = new ArrayList<>();
        List<Piece> blackSet = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Piece piece = getPiece(pawns.get(i).get(j), i, j);
                if(piece != null) {
                    if(piece.type == Piece.PieceType.KING) {
                        if (piece.color == WHITE) {
                            whiteSet.add(0, piece);
                        } else {
                            blackSet.add(0, piece);
                        }
                    } else {
                        if (piece.color == WHITE) {
                            whiteSet.add(piece);
                        } else {
                            blackSet.add(piece);
                        }
                    }
                }
            }
        }
        return new Board(new Set(WHITE, whiteSet), new Set(Color.BLACK, blackSet));
    }

//    @Test
//    public void BoardFactoryTest() {
//        List<String> board = new ArrayList<>(Arrays.asList(
//        " |0|1|2|3|4|5|6|7|",
//        "0|♖|♘|♗|♕|♔|♗|♘|♖|",
//        "1|♙|♙|♙|♙|♙|♙|♙|♙|",
//        "2|_|_|_|_|_|_|_|_|",
//        "3|_|_|_|_|_|_|_|_|",
//        "4|_|_|_|_|_|_|_|_|",
//        "5|_|_|_|_|_|_|_|_|",
//        "6|♟|♟|♟|♟|♟|♟|♟|♟|",
//        "7|♜|♞|♝|♛|♚|♝|♞|♜|"));
//        System.out.print(createBoard(board).toString());
//    }

}

package com.ps.server.Logic;

import com.ps.server.Logic.Pieces.*;
import java.util.ArrayList;
import java.util.List;

import static com.ps.server.Logic.Color.*;
import static com.ps.server.Logic.Pieces.Piece.PieceType.*;

public class Board {
    /** number of columns on board, numerated 0-7*/
    public final static int COL_NUM = 8;
    /** number of rows on board, numerated 0-7*/
    public final static int ROW_NUM = 8;
    private Piece[][] board;
    private Set whiteSet;
    private Set blackSet;

    /**
     * Class constructor.
     * @param whiteSet white pieces set
     * @param blackSet black pieces set
     */
    public Board(Set whiteSet, Set blackSet) {
        assert(whiteSet.color == WHITE && blackSet.color == BLACK);
        this.whiteSet = whiteSet;
        this.blackSet = blackSet;
        board = generateBoardFromSets(whiteSet, blackSet);
    }

    /**
     * Gets the state of given position on board
     * @param position on board of the square (in bounds: row: 0-7 column: 0-7)
     * @return instance of ChessSquareState that will contain following information:
     *                  - if square is occupied
     *                  - piece that the square is occupied by
     */
    public ChessSquareState getChessSquareState(Position position) {
        if(position.row < 0 || position.col < 0 || position.col > 7 || position.row > 7)
            return null;
        return new ChessSquareState(board[position.row][position.col]);
    }

    /**
     * Checks if position is empty
     * @param position on board of the square (in bounds: row: 0-7 column: 0-7)
     * @return true if chess square in given position is empty
     */
    public boolean ifEmpty(Position position) {
        ChessSquareState state = getChessSquareState(position);
        return state != null && state.state == ChessSquareState.State.EMPTY;
    }

    /**
     * Checks if position is occupied by an opponent
     * @param position on board of the square (in bounds: row: 0-7 column: 0-7)
     * @param color of the piece (the opponent should have contrary)
     * @return true if chess square is occupied by an opponent
     */
    public boolean ifOccupiedByOpponent(Position position, Color color) {
        ChessSquareState state = getChessSquareState(position);
        return state != null && state.state == ChessSquareState.State.OCCUPIED && state.getPiece().color != color;
    }

    /**
     * Gets from every piece of given color set list of PRE SAVED moves and combines them
     *              moves should be first generated by update game method
     * @param color of the set that moves should be get for
     * @return list of all legal moves for given set color
     */
    public List<Move> getLegalMoves(Color color) {
        Set set = (color == WHITE) ? whiteSet : blackSet;
        return set.legalMoves();
    }

    private Piece removePiece(Position loc) {
        Piece p = board[loc.row][loc.col];
        if(p != null) {
            p.move(null);
        }
        board[loc.row][loc.col] = null;
        return p;
    }

    private void addPiece(Piece piece, Position loc) {
        board[loc.row][loc.col] = piece;
        piece.move(loc);
    }

    private void giveEnPassantsRights(Position destination, Position loc, Color color) {
        for(int i = -1; i <= 2; i += 2) {
            int column = destination.col + i;
            if(column < 0 || column > 7) continue;
            Piece piece = board[destination.row][column];
            if(piece != null && piece.color != color) {
              piece.giveEnPassantRights(loc);
            }
        }
    }

    /**
     * Makes a move on board
     * Used move should be firstly validated by validatePlayersMove and
     * only instance of Move returned by that method should be used
     * @param move pre validated move
     */
    public void makeMove(Move move) {
        Position loc = move.loc;
        Position dest = move.dest;
        switch (move.type) {
            case PROMOTION:
                removePiece(dest);
                removePiece(loc);
                addPiece(Piece.createPiece(move.getPromoteTo(), dest, move.pieceColor), dest);
                break;
            case EN_PASSANT:
                removePiece(new Position(loc.row, dest.col));
            case LONG_PAWN_MOVE:
                giveEnPassantsRights(dest, loc, move.pieceColor);
            case NORMAL:
                removePiece(dest);
                addPiece(removePiece(loc), dest);
                break;
            case LONG_CASTLE: {
                Piece rook = removePiece(dest);
                Piece king = removePiece(loc);
                addPiece(king, new Position(loc.row, loc.col - 2));
                addPiece(rook, new Position(dest.row, dest.col + 3));
                break;
            }
            case SHORT_CASTLE: {
                Piece rook = removePiece(dest);
                Piece king = removePiece(loc);
                addPiece(king, new Position(loc.row, loc.col + 2));
                addPiece(rook, new Position(dest.row, dest.col - 2));
                break;
            }
        }
    }

    /**
     * Returns the list of changes that given move would do but doesn't make changes on the board
     * Used move should be firstly validated by validatePlayersMove and
     * only instance of Move returned by that method should be used
     * @param move pre validated move
     */
    static List<Change> getListOfChanges(Move move) {
        List<Change> changes = new ArrayList<>();
        Position loc = move.loc;
        Position dest = move.dest;
        Color color = move.pieceColor;
        switch (move.type) {
            case PROMOTION:
                changes.add(new Change(loc, null, null));
                changes.add(new Change(dest, move.getPromoteTo(), color));
                break;
            case EN_PASSANT:
                changes.add(new Change(new Position(loc.row, dest.col), null, null));
            case LONG_PAWN_MOVE:
            case NORMAL:
                changes.add(new Change(loc, null, null));
                changes.add(new Change(dest, move.pieceType, color));
                break;
            case LONG_CASTLE:
                changes.add(new Change(loc, null, null));
                changes.add(new Change(dest, null, null));
                changes.add(new Change(new Position(loc.row, loc.col - 2), KING, color));
                changes.add(new Change(new Position(dest.row, dest.col + 3), ROOK, color));
                break;
            case SHORT_CASTLE:
                changes.add(new Change(loc, null, null));
                changes.add(new Change(dest, null, null));
                changes.add(new Change(new Position(loc.row, loc.col + 2), KING, color));
                changes.add(new Change(new Position(dest.row, dest.col - 2), ROOK, color));
                break;
        }
        return changes;
    }

    /**
     * Updates game state.
     * Generates all legal moves for the set that will move next.
     * Returns the state of the game.
     * @param turn color of the set that will move next
     * @return game state (CHECKMATE, STALEMATE, GAME_RUNNING)
     *              if CHECKMATE then the winner is the player that made a move before update
     */
    public GameState updateGame(Color turn) {
        Set playingSet = (turn == WHITE) ? whiteSet : blackSet;
        playingSet.generateLegalMoves();
        if(!playingSet.checkIfCanMove()) {
            if(checkIfKingInCapture(turn)) {
                return GameState.CHECKMATE;
            }
            return GameState.STALEMATE;
        }
        return GameState.GAME_RUNNING;
    }

    /**
     * Validates players move.
     * @param color players color
     * @param loc position of the piece that player wants to move
     * @param dest destination of the piece that player wants to move
     * if the move is a castle then location should be kings position and destination rooks position
     * @return instance of class move,
     *             that can be used later to give as parameter to makeMove method to actually make move on board
     */
    public Move validatePlayersMove(Position loc, Position dest, Color color) {
        Piece piece = board[loc.row][loc.col];
        if(piece == null || piece.color != color) { return null; }
        return piece.getMoveTo(dest);
    }

    private boolean checkIfKingInCapture(Color color) {
        Set thisSet = (color == WHITE) ? whiteSet : blackSet;
        Set oppositeSet = (color == WHITE) ? blackSet : whiteSet;
        return oppositeSet.checkIfCanCaptureKingOn(thisSet.getKingsPosition());

    }

    /**
     * Validates given move in the following way:
     *      Makes copy of the board.
     *      Makes on that copy given move.
     *      Checks if the king in color of the making move piece is not under capture.
     * @param move move to be validated
     * @return true if after move king is NOT under capture, means that given move is valid
     */
    public boolean validateIfAfterMoveKingNotInCapture(Move move) {
        Board boardCopy = copy();
        boardCopy.makeMove(move);
        return !boardCopy.checkIfKingInCapture(move.pieceColor);
    }

    private Piece[][] generateBoardFromSets(Set set1, Set set2) {
        Piece[][] board = new Piece[ROW_NUM][];
        for(int i = 0; i < ROW_NUM; i++) {
            board[i] = new Piece[COL_NUM];
        }
        set1.addSetToBoard(board);
        set1.addBoardToPieces(this);
        set2.addSetToBoard(board);
        set2.addBoardToPieces(this);
        return board;
    }

    /**
     * Makes and returns a copy of the board. Uses copies of sets and pieces.
     * @return copied board
     */
    public Board copy() {
        return new Board(whiteSet.copy(), blackSet.copy());
    }

    /**
     * {@inheritDoc}
     */
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
                builder.append("|");
                Piece piece = board[row][col];
                if(piece == null) {
                    builder.append(" ");
                } else {
                    builder.append(piece.toString());
                }
            }
            builder.append("|\n");
        }
        return builder.toString();
    }

    /**
     * @return board, a 8(rows)x8(columns) array of pieces, empty squares are null
     */
    public Piece[][] getBoard() {
        return board;
    }
}

package com.ps.server.logic.game;

import com.ps.server.dto.PieceDTO;
import com.ps.server.enums.GameType;
import com.ps.server.exception.CannotJoinPlayerException;
import com.ps.server.exception.NotPlayerTurnException;
import com.ps.server.exception.NotValidMoveException;
import com.ps.server.logic.*;
import com.ps.server.logic.pieces.Piece;
import com.ps.server.logic.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class Game {

    private Player firstPlayer;

    private Player secondPlayer;

    private Board board;

    private GameStatus gameStatus;

    private GameType gameType;

    /**
     * Makes given move on board.
     *
     * @param origin      Origin of the move.
     * @param destination Destination of the move.
     * @param player      Player who wants to make a move.
     * @return List of Changes on Board, which occur after move, e.g. PROMOTION.
     * @throws NotPlayerTurnException when {@param player} wants to make move when it is not its turn
     * @throws NotValidMoveException  when {@param move} is not valid
     */
    public List<Change> makeMove(Position origin, Position destination, Player player) throws NotPlayerTurnException, NotValidMoveException {
        if (isPlayerTurn(player)) {
            board.updateGame(player.getColor());
            Move move = board.validatePlayersMove(origin, destination, player.getColor());
            if (isMoveValid(move)) {
                board.makeMove(move);
                //TODO RS: Before changing game's turn should check for CHECKMATE or PAT
                flipTurn();
                return board.getListOfChanges(move);
            } else {
                //TODO RS: Give reason why
                throw new NotValidMoveException();
            }
        } else {
            throw new NotPlayerTurnException();
        }
    }

    public boolean isPlayerTurn(Player player) {
        return (this.gameStatus == GameStatus.FIRST_PLAYER_TURN && this.firstPlayer.equals(player))
                || (this.gameStatus == GameStatus.SECOND_PLAYER_TURN && this.secondPlayer.equals(player));
    }

    private boolean isMoveValid(Move move) {
        return (move != null);
    }

    private void flipTurn() {
        if (gameStatus == GameStatus.FIRST_PLAYER_TURN) {
            gameStatus = GameStatus.SECOND_PLAYER_TURN;
        } else if (gameStatus == GameStatus.SECOND_PLAYER_TURN) {
            gameStatus = GameStatus.FIRST_PLAYER_TURN;
        }
    }

    /**
     * Joins player to game.
     *
     * @param player to be joined to game.
     * @throws CannotJoinPlayerException when game has already started (there are two players in game)
     */
    public void joinPlayer(Player player) throws CannotJoinPlayerException {
        if (isPossibleToJoinPlayer()) {
            secondPlayer = player;
            gameStatus = GameStatus.FIRST_PLAYER_TURN;

        } else {
            throw new CannotJoinPlayerException();
        }

    }

    private boolean isPossibleToJoinPlayer() {
        return secondPlayer == null && gameStatus == GameStatus.WAITING_FOR_SECOND_PLAYER;
    }

    /**
     * Returns whole Board described by List of PieceDTO
     *
     * @return List of PieceDTO on the board
     */
    public List<PieceDTO> getPieceDTOList() {
        List<PieceDTO> pieceDTOList = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                PieceDTO pieceDTO=createPieceDTO(row,column);
                pieceDTOList.add(pieceDTO);
            }
        }
        return pieceDTOList;
    }

    private PieceDTO createPieceDTO(int row, int column) {
        Piece[][] pieces = board.getBoard();
        Piece piece = pieces[row][column];
        List<Position> possibleMoves = null;
        Piece.PieceType type = null;
        Color color = null;
        if (piece != null) {
            piece.legalMoves();
            List<Move> legalMoves = piece.getLegalMoves();
            possibleMoves = getPossibleMoves(legalMoves);
            type = piece.getType();
            color = piece.color;
        }

        return new PieceDTO(row,
                column,
                type,
                color,
                possibleMoves);
    }

    private List<Position> getPossibleMoves(List<Move> legalMoves) {
        List<Position> possibleMoves = new ArrayList<>();
        if (legalMoves != null && !legalMoves.isEmpty()) {
            for (Move move : legalMoves) {
                possibleMoves.add(move.dest);
            }
        }
        return possibleMoves;
    }

    /**
     * Return possible moves for Piece located on Position.
     *
     * @param position Postition on which Piece is located.
     * @return List of possible moves.
     */
    public List<Position> getPossibleMovesForPosition(Position position) {
        Piece[][] pieces = board.getBoard();
        Piece piece = pieces[position.row][position.column];
        if (piece != null) {
            piece.legalMoves();
            List<Move> legalMoves = piece.getLegalMoves();
            return getPossibleMoves(legalMoves);
        }
        return Collections.emptyList();
    }
}

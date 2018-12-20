package com.ps.server.Logic.game;

import com.ps.server.AI.BotController;
import com.ps.server.Logic.*;
import com.ps.server.Logic.Pieces.Piece;
import com.ps.server.Logic.player.BotPlayer;
import com.ps.server.Logic.player.Player;
import com.ps.server.dto.PieceDTO;
import com.ps.server.enums.Result;
import com.ps.server.exception.CannotJoinPlayerException;
import com.ps.server.exception.GameHasFinishedException;
import com.ps.server.exception.NotPlayerTurnException;
import com.ps.server.exception.NotValidMoveException;
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

    private BotController controller;

    private GameState gameState;

    private Result result = null;

    private boolean isPromotion = false;

    private Move promotionMove;


    /**
     * Makes given Move on board.
     *
     * @param origin      Origin of the Move.
     * @param destination Destination of the Move.
     * @param player      Player who wants to make a Move.
     * @return List of Changes on Board, which occur after Move, e.g. PROMOTION.
     * @throws NotPlayerTurnException when {@param Player} wants to make Move when it is not its turn
     * @throws NotValidMoveException  when {@param Move} is not valid
     */
    public List<Change> makeMove(Position origin, Position destination, Player player) throws NotPlayerTurnException, NotValidMoveException, GameHasFinishedException {
        if (isPlayerTurn(player)) {
            updateGame(player);
            if (gameState == GameState.GAME_RUNNING) {
                Move move = board.validatePlayersMove(origin, destination, player.getColor());
                if (isMoveValid(move)) {
                    if (move.type != Move.MoveType.PROMOTION) {
                        board.makeMove(move);
                        flipTurn();
                        updateAfterMove(player);
                        isPromotion = false;
                        return board.getListOfChanges(move);
                    } else {
                        isPromotion = true;
                        promotionMove = move;
                        return Collections.emptyList();
                    }
                } else {
                    //TODO RS: Give reason why
                    throw new NotValidMoveException();
                }
            } else {
                throw new GameHasFinishedException();
            }
        } else {
            throw new NotPlayerTurnException();
        }
    }

    public List<Change> promote(Player player, Piece.PieceType promotionType){
        promotionMove.setPromoteTo(promotionType);
        board.makeMove(promotionMove);
        flipTurn();
        updateAfterMove(player);
        isPromotion = false;
        return board.getListOfChanges(promotionMove);
    }

    private void updateAfterMove(Player player) {
        if (player.equals(firstPlayer)) {
            updateGame(secondPlayer);
        } else {
            updateGame(firstPlayer);
        }
    }

    private void updateGame(Player player) {
        gameState = board.updateGame(player.getColor());
        if (result == null) {
            if (gameState == GameState.STALEMATE) {
                result = Result.DRAW;
            } else if (gameState == GameState.CHECKMATE) {
                if (player.equals(firstPlayer)) {
                    result = Result.SECOND_PLAYER_WON;
                } else {
                    result = Result.FIRST_PLAYER_WON;
                }
            }
        }
    }

    public List<Change> makeMoveBot() throws NotPlayerTurnException, GameHasFinishedException {
        if (isPlayerTurn(secondPlayer) && secondPlayer instanceof BotPlayer) {
            updateGame(secondPlayer);
            if (gameState == GameState.GAME_RUNNING) {
                BotController controller = getBotController();
                Move botMove = controller.execute(board, secondPlayer.getColor());
                board.makeMove(botMove);
                flipTurn();
                updateAfterMove(secondPlayer);
                return board.getListOfChanges(botMove);
            } else {
                throw new GameHasFinishedException();
            }

        }
        throw new NotPlayerTurnException();
    }

    private BotController getBotController() {
        if (controller == null) {
            controller = new BotController();
        }
        return controller;
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
     * Joins Player to Game.
     *
     * @param player to be joined to Game.
     * @throws CannotJoinPlayerException when Game has already started (there are two players in Game)
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
                PieceDTO pieceDTO = createPieceDTO(row, column);
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
            piece.update();
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
        Piece piece = pieces[position.row][position.col];
        if (piece != null) {
            piece.update();
            List<Move> legalMoves = piece.getListOfMoves();
            return getPossibleMoves(legalMoves);
        }
        return Collections.emptyList();
    }

}

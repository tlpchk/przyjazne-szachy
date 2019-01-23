package com.ps.server.controller;

import com.ps.server.exception.*;
import com.ps.server.Logic.Position;
import com.ps.server.dto.*;
import com.ps.server.entity.PlayerEntity;
import com.ps.server.service.GameService;
import com.ps.server.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;

    /**
     * Creates new Game.
     *
     * @param createGameDTO CreateGameDTO contains ids of players willing to play in Game.
     * @return Id of newly created Game.
     */
    @RequestMapping(method = RequestMethod.POST)
    public Long createGame(@RequestBody CreateGameDTO createGameDTO) throws InvalidRequiredArgumentException, SamePlayerException {
        PlayerEntity firstPlayerEntity = playerService.getPlayerEntity(createGameDTO.getFirstPlayerId());
        PlayerEntity secondPlayerEntity = playerService.getPlayerEntity(createGameDTO.getSecondPlayerId());
        return gameService.createNewGame(firstPlayerEntity, secondPlayerEntity, createGameDTO.isRanked());
    }

    /**
     * Returns list of games which can be joined to.
     *
     * @return list of games which can be joined to
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Long> getGamesToJoin() {
        return gameService.getGamesToJoin();
    }

    /**
     * Joins Player to Game.
     *
     * @param gameId   Id of the Game to which Player wants to join
     * @param playerId Id of the Player who wants to join Game
     * @return true if joined successfully, false otherwise
     * @throws GameNotExistException when Game with given Game id does not exist
     */
    @RequestMapping(value = "/{gameId}/join", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean joinGame(@PathVariable Long gameId, @RequestBody Long playerId) throws GameNotExistException {
        PlayerEntity playerEntity = playerService.getPlayerEntity(playerId);
        try {
            gameService.joinGame(gameId, playerEntity);
            return true;
        } catch (InvalidRequiredArgumentException | CannotJoinPlayerException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Makes Move in given Game.
     *
     * @param gameId        Id of the Game in which Move should be made.
     * @param createMoveDTO CreateMoveDTO describes Move which should be made.
     * @return MoveResponseDTO with field wasMoveValid set to true if Move was valid, set to false otherwise
     * @throws InvalidRequiredArgumentException when Player does not have playerType set
     * @throws NotPlayerTurnException           when Player wants to make Move when it is not its turn
     * @throws GameNotExistException            when Game with given Game id does not exist
     */
    @RequestMapping(value = "/{gameId}/move", method = RequestMethod.POST)
    public MoveResponseDTO makeMove(@PathVariable Long gameId, @RequestBody CreateMoveDTO createMoveDTO) throws GameNotExistException, InvalidRequiredArgumentException, NotPlayerTurnException, GameHasFinishedException {
        PlayerEntity playerEntity = playerService.getPlayerEntity(createMoveDTO.getPlayerId());
        MoveResponseDTO move = gameService.makeMove(gameId, playerEntity, createMoveDTO.getOrigin(), createMoveDTO.getDestination());
        gameService.runBotIfRelevant(gameId);
        return move;
    }

    /**
     * Promotes
     *
     * @param gameId       Id of the Game in which Promotion should be made.
     * @param promotionDTO PromotionDTO describes Promotion which should be made on board
     * @return MoveResponseDTO with field wasMoveValid set to true if Move was valid, set to false otherwise
     * @throws GameNotExistException            when Game with given Game id does not exist
     * @throws InvalidRequiredArgumentException when Player does not have playerType set
     * @throws NotPlayerTurnException           when Player wants to make Move when it is not its turn
     * @throws GameHasFinishedException         when Game with given Game id has finished
     */
    @RequestMapping(value = "/{gameId}/promote", method = RequestMethod.POST)
    public MoveResponseDTO promote(@PathVariable Long gameId, @RequestBody PromotionDTO promotionDTO) throws GameNotExistException, InvalidRequiredArgumentException, NotPlayerTurnException, GameHasFinishedException {
        PlayerEntity playerEntity = playerService.getPlayerEntity(promotionDTO.getPlayerId());
        return gameService.promote(gameId, playerEntity, promotionDTO.getPieceType());
    }

    /**
     * Gets board from Game.
     *
     * @param gameId Id of the Game.
     * @return List of PieceDTO describing Board.
     * @throws GameNotExistException when Game with given Game id does not exist
     */
    @RequestMapping(value = "/{gameId}/board", method = RequestMethod.GET)
    public List<PieceDTO> getBoard(@PathVariable Long gameId) throws GameNotExistException {
        return gameService.getBoard(gameId);
    }

    /**
     * Gets possible moves for given Position.
     *
     * @param gameId   Id of the Game.
     * @param position For which possible moves should be calculated.
     * @return List of Position of possible moves.
     */
    @RequestMapping(value = "/{gameId}/possibleMoves", method = RequestMethod.POST)
    public List<Position> getPossibleMoves(@PathVariable Long gameId, @RequestBody Position position) {
        return gameService.getPossibleMoves(gameId, position);
    }

    /**
     * Returns updates about done Moves in Game.
     *
     * @param gameId   Id of the Game.
     * @param updateId Id of the Update
     * @return MoveUpdateDTO of given updateId
     */
    @RequestMapping(value = "/{gameId}/update/{updateId}", method = RequestMethod.GET)
    public MoveUpdateDTO getUpdate(@PathVariable Long gameId, @PathVariable Integer updateId) {
        return gameService.getUpdate(gameId, updateId);
    }

    /**
     * Returns info about game.
     *
     * @param gameId   Id of the Game.
     * @param playerId Id of the Player
     * @return info about game.
     * @throws GameNotExistException            when Game with given Game id does not exist
     * @throws InvalidRequiredArgumentException when Player does not have playerType set
     */
    @RequestMapping(value = "/{gameId}", method = RequestMethod.POST)
    public GameInfoDTO getGameInfo(@PathVariable Long gameId, @RequestBody Long playerId) throws GameNotExistException, InvalidRequiredArgumentException {
        PlayerEntity playerEntity = playerService.getPlayerEntity(playerId);
        return gameService.getGameInfo(gameId, playerEntity);
    }

    /**
     * Returns left time in game for player.
     *
     * @param gameId   Id of the Game.
     * @param playerId Id of the Player
     * @return left time in game for player.
     * @throws GameNotExistException            when Game with given Game id does not exist
     * @throws InvalidRequiredArgumentException when Player does not have playerType set
     */
    @RequestMapping(value = "/{gameId}/timer", method = RequestMethod.POST)
    public Long getLeftTimeInSeconds(@PathVariable Long gameId, @RequestBody Long playerId) throws GameNotExistException, InvalidRequiredArgumentException {
        PlayerEntity playerEntity = playerService.getPlayerEntity(playerId);
        return gameService.getTimer(gameId, playerEntity);
    }

}

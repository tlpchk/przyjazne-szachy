package com.ps.server.controller;

import com.ps.server.exception.*;
import com.ps.server.Logic.Position;
import com.ps.server.dto.*;
import com.ps.server.entity.PlayerEntity;
import com.ps.server.service.GameService;
import com.ps.server.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
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
        return gameService.makeMove(gameId, playerEntity, createMoveDTO.getOrigin(), createMoveDTO.getDestination());
    }

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

    @RequestMapping(value = "/{gameId}/possibleMoves", method = RequestMethod.POST)
    public List<Position> getPossibleMoves(@PathVariable Long gameId, @RequestBody Position position) {
        return gameService.getPossibleMoves(gameId, position);
    }

    //TODO RS: remove it, funcionality should be implemented in getGameInfo
    @RequestMapping(value = "/{gameId}/update/last", method = RequestMethod.GET)
    public MoveUpdateDTO getLastUpdate(@PathVariable Long gameId) {
        return gameService.getLastUpdate(gameId);
    }

    @RequestMapping(value = "/{gameId}/update/{updateId}", method = RequestMethod.GET)
    public MoveUpdateDTO getUpdate(@PathVariable Long gameId, @PathVariable Integer updateId) {
        System.out.println("get update: " + updateId);
        return gameService.getUpdate(gameId, updateId);
    }

    @RequestMapping(value = "/{gameId}/bot", method = RequestMethod.GET)
    public MoveResponseDTO moveBot(@PathVariable Long gameId) throws GameNotExistException, GameHasFinishedException {
        try {
            return gameService.makeMoveBot(gameId);
        } catch (NotPlayerTurnException e) {
            return new MoveResponseDTO();
        }
    }

    //TODO RS: add timer here maybe
    @RequestMapping(value = "/{gameId}", method = RequestMethod.POST)
    public GameInfoDTO getGameInfo(@PathVariable Long gameId, @RequestBody Long playerId) throws GameNotExistException, InvalidRequiredArgumentException {
        PlayerEntity playerEntity = playerService.getPlayerEntity(playerId);
        return gameService.getGameInfo(gameId, playerEntity);
    }

}

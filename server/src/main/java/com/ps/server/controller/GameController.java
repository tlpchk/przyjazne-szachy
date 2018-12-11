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
     * Creates new game.
     *
     * @param createGameDTO CreateGameDTO contains ids of players willing to play in game.
     * @return Id of newly created game.
     */
    @RequestMapping(method = RequestMethod.POST)
    public Long createGame(@RequestBody CreateGameDTO createGameDTO) throws InvalidRequiredArgumentException, SamePlayerException {
        PlayerEntity firstPlayerEntity = playerService.getPlayerEntity(createGameDTO.getFirstPlayerId());
        PlayerEntity secondPlayerEntity = playerService.getPlayerEntity(createGameDTO.getSecondPlayerId());
        return gameService.createNewGame(firstPlayerEntity, secondPlayerEntity);
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
     * Joins player to game.
     *
     * @param gameId   Id of the game to which player wants to join
     * @param playerId Id of the player who wants to join game
     * @return true if joined successfully, false otherwise
     * @throws GameNotExistException            when game with given game id does not exist
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
     * Makes move in given game.
     *
     * @param gameId        Id of the game in which move should be made.
     * @param createMoveDTO CreateMoveDTO describes move which should be made.
     * @return MoveResponseDTO with field wasMoveValid set to true if move was valid, set to false otherwise
     * @throws InvalidRequiredArgumentException when player does not have playerType set
     * @throws NotPlayerTurnException           when player wants to make move when it is not its turn
     * @throws GameNotExistException            when game with given game id does not exist
     */
    @RequestMapping(value = "/{gameId}/move", method = RequestMethod.POST)
    public MoveResponseDTO makeMove(@PathVariable Long gameId, @RequestBody CreateMoveDTO createMoveDTO) throws GameNotExistException, InvalidRequiredArgumentException, NotPlayerTurnException {
        PlayerEntity playerEntity = playerService.getPlayerEntity(createMoveDTO.getPlayerId());
        return gameService.makeMove(gameId, playerEntity, createMoveDTO.getOrigin(), createMoveDTO.getDestination());
    }

    /**
     * Gets board from game.
     *
     * @param gameId Id of the game.
     * @return List of PieceDTO describing Board.
     * @throws GameNotExistException when game with given game id does not exist
     */
    @RequestMapping(value = "/{gameId}/board", method = RequestMethod.GET)
    public List<PieceDTO> getBoard(@PathVariable Long gameId) throws GameNotExistException {
        return gameService.getBoard(gameId);
    }

    @RequestMapping(value = "/{gameId}/possibleMoves", method = RequestMethod.POST)
    public List<Position> getPossibleMoves(@PathVariable Long gameId, @RequestBody Position position) {
        return gameService.getPossibleMoves(gameId, position);
    }


    @RequestMapping(value = "/{gameId}/update", method = RequestMethod.GET)
    public MoveUpdateDTO getLastUpdate(@PathVariable Long gameId) throws GameNotExistException {
        return gameService.getLastUpdate(gameId);
    }

    //TODO RS: zapytanie na "/{gameId}" zwraca info o grze, m.in czyja tura


}

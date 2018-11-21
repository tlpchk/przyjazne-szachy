package com.ps.server.controller;

import com.ps.server.dto.CreateMoveDTO;
import com.ps.server.dto.MoveResponseDTO;
import com.ps.server.entity.GameEntity;
import com.ps.server.dto.GameDTO;
import com.ps.server.dto.PlayerDTO;
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

    @RequestMapping(method = RequestMethod.POST)
    public GameDTO createGame(@RequestBody PlayerDTO playerDTO) {
        PlayerEntity playerEntity = playerService.getPlayer(playerDTO.getId());
        //TODO RS: parametrizite it somehow
        GameEntity gameEntity = gameService.createNewGame(playerEntity, null);
        return new GameDTO(gameEntity.getId());
    }

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GameEntity> getGamesToJoin() {
        return gameService.getGamesToJoin();
    }

    @RequestMapping(value = "/{gameId}/join", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean joinGame(@PathVariable Long gameId, @RequestBody PlayerDTO playerDTO) {
        PlayerEntity player = playerService.getPlayer(playerDTO.getId());
        return gameService.joinGame(gameId, player);
    }

//    //TODO RS: should check it in logic
//    @RequestMapping(value = "/{gameId}/turn", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public boolean isPlayerTurn(@RequestBody PlayerDTO playerDTO) {
//        return moveService.isPlayerTurn(gameService.getGameEntity(playerDTO.getId()), playerService.getPlayer(playerDTO.getId()));
//    }

    @RequestMapping(value = "/{gameId}/move", method = RequestMethod.POST)
    public MoveResponseDTO createMove(@PathVariable Long gameId, @RequestBody CreateMoveDTO createMoveDTO) {
        PlayerEntity playerEntity = playerService.getPlayer(createMoveDTO.getPlayerId());
        return gameService.makeMove(gameId, playerEntity, createMoveDTO.getOrigin(), createMoveDTO.getDestination());
    }


}

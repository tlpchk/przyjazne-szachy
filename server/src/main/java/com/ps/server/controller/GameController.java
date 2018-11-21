package com.ps.server.controller;

import com.ps.server.dto.CreateMoveDTO;
import com.ps.server.entity.GameEntity;
import com.ps.server.dto.GameDTO;
import com.ps.server.dto.PlayerDTO;
import com.ps.server.entity.PlayerEntity;
import com.ps.server.service.GameService;
import com.ps.server.service.MoveService;
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

    @Autowired
    private MoveService moveService;


    @RequestMapping(method = RequestMethod.POST)
    public GameDTO createGame(@RequestBody PlayerDTO playerDTO) {
        PlayerEntity player = playerService.getPlayer(playerDTO.getId());
        GameEntity gameEntity = gameService.createNewGame(player);
        return new GameDTO(gameEntity.getId());
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GameEntity> getGamesToJoin() {
        return gameService.getGamesToJoin();
    }

    @RequestMapping(value = "/{gameId}/join", method = RequestMethod.POST)
    public GameDTO joinGame(@PathVariable Long gameId, @RequestBody PlayerDTO playerDTO) {
        PlayerEntity player = playerService.getPlayer(playerDTO.getId());
        GameEntity gameEntity = gameService.joinGame(gameId, player);
        return new GameDTO(gameEntity.getId());
    }

    //TODO RS: should check it in logic
    @RequestMapping(value = "/{gameId}/turn", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean isPlayerTurn(@RequestBody PlayerDTO playerDTO) {
        return moveService.isPlayerTurn(gameService.getGame(playerDTO.getId()), playerService.getPlayer(playerDTO.getId()));
    }

    @RequestMapping(value = "/{gameId}/move", method = RequestMethod.POST)
    public void createMove(@PathVariable Long gameId, @RequestBody CreateMoveDTO createMoveDTO) {

        System.out.println(createMoveDTO.getDestination().col);
        System.out.println(createMoveDTO.getDestination().row);
//        logger.info("move to insert:" + createMoveDTO.getBoardColumn() + createMoveDTO.getBoardRow());
//
//
//        Move move = moveService.createMove(gameService.getGame(gameId), playerService.getLoggedUser(), createMoveDTO);
//        Game game = gameService.getGame(gameId);
//        gameService.updateGameStatus(gameService.getGame(gameId), moveService.checkCurrentGameStatus(game));

        //TODO RS:should return smth
    }


}

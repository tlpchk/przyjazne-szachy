package com.ps.server.controller;

import com.ps.server.domain.Game;
import com.ps.server.domain.Move;
import com.ps.server.dto.CreateMoveDTO;
import com.ps.server.dto.MoveResponseDTO;
import com.ps.server.dto.PlayerDTO;
import com.ps.server.service.GameService;
import com.ps.server.service.MoveService;
import com.ps.server.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/move")
public class MoveController {

    @Autowired
    private GameService gameService;

    @Autowired
    private MoveService moveService;

    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/turn", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean isPlayerTurn(@RequestBody PlayerDTO playerDTO) {
        return moveService.isPlayerTurn(gameService.getGame(playerDTO.getId()), playerService.getPlayer(playerDTO.getId()));
    }

//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public MoveResponseDTO createMove(@RequestBody CreateMoveDTO createMoveDTO) {
//        Move move = moveService.createMove(createMoveDTO);
//        Game game = gameService.getGame(gameId);
//        gameService.updateGameStatus(gameService.getGame(gameId), moveService.checkCurrentGameStatus(game));
//
//        return move;
//    }
}

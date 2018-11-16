package com.ps.server.controller;

import com.ps.server.domain.Game;
import com.ps.server.dto.GameDTO;
import com.ps.server.dto.PlayerDTO;
import com.ps.server.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public GameDTO createNewGame() {
        Game game = gameService.createNewGame();
        PlayerDTO playerDTO = new PlayerDTO(game.getFirstPlayer().getId());
        GameDTO gameDTO = new GameDTO(game.getId(),playerDTO);
        return gameDTO;
    }

    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Game> getGamesToJoin() {
        return gameService.getGamesToJoin();
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public GameDTO joinGame(@RequestBody GameDTO gameDTO) {
        Game game = gameService.joinGame(gameDTO.getId());
        PlayerDTO playerDTO = new PlayerDTO(game.getSecondPlayer().getId());
        GameDTO gameToReturn = new GameDTO(game.getId(),playerDTO);
        return gameToReturn;
    }
}

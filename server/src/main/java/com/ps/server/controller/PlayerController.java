package com.ps.server.controller;


import com.ps.server.dto.CreatePlayerDTO;
import com.ps.server.entity.PlayerEntity;
import com.ps.server.enums.PlayerType;
import com.ps.server.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final String BOT_USERNAME = "bot";

    @Autowired
    PlayerService playerService;

    /**
     * Creates new Player
     *
     * @param createPlayerDTO describes newPlayer parameters.
     * @return Id of newly created player.
     */
    @RequestMapping(method = RequestMethod.POST)
    public Long createPlayer(@RequestBody CreatePlayerDTO createPlayerDTO) {
        String username;
        if (createPlayerDTO.getPlayerType() == PlayerType.HUMAN) {
            username = createPlayerDTO.getUsername();
        } else {
            username = BOT_USERNAME;
        }
        PlayerEntity playerEntity = playerService.createNewPlayer(username, createPlayerDTO.getColor(), createPlayerDTO.getPlayerType());
        return playerEntity.getId();
    }

}

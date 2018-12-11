package com.ps.server.controller;

import com.ps.server.Logic.Color;
import com.ps.server.entity.PlayerEntity;
import com.ps.server.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    //TODO RS:Add color checking and throwing exceptions
    @RequestMapping(value = "/humans",method = RequestMethod.POST)
    public Long createHumanPlayer(@RequestBody String colorString) {
        Color color = (colorString.toUpperCase().equals("WHITE")) ? Color.WHITE : Color.BLACK;
        PlayerEntity playerEntity = playerService.createNewHumanPlayer(color);
        return playerEntity.getId();
    }

    @RequestMapping(value = "/bots",method = RequestMethod.POST)
    public Long createBot(@RequestBody String colorString) {
        Color color = (colorString.toUpperCase().equals("WHITE")) ? Color.WHITE : Color.BLACK;
        PlayerEntity playerEntity = playerService.createNewBot(color);
        return playerEntity.getId();
    }
}

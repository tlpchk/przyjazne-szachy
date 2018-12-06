package com.ps.server.controller;

import com.ps.server.logic.Color;
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

    //TODO RS:Add color checking and thronig exceptions
    @RequestMapping(method = RequestMethod.POST)
    public Long createPlayer(@RequestBody String colorString) {
        Color color = (colorString.toUpperCase().equals("WHITE")) ? Color.WHITE : Color.BLACK;
        PlayerEntity playerEntity = playerService.createNewPlayer(color);
        return playerEntity.getId();
    }

    //TODO RS: createBot
}
